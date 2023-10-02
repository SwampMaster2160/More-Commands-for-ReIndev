package com.swampmaster2160.morecommandsforreindev;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.entity.player.EntityPlayer;
import net.minecraft.src.game.level.World;

public abstract class EntityTargets {
	public static @Nullable Entity[] getTargetsFromString(World world, String string, double x, double y, double z, Entity executerEntity) {
		//Entity[] out = new Entity[0];
		//return out;
		Object out = evaluateTokenFirstRound(world, string, x, y, z, executerEntity);
		if (out == null) return null;
		if (!(out instanceof Entity[])) return null;
		return (Entity[])out;
	}

	public static @Nullable Object evaluateTokenFirstRound(World world, String token, double x, double y, double z, @Nullable Entity executerEntity) {
		if (token.startsWith("@")) {
			String tokenWithoutPrefix = token.substring(1);
			switch (tokenWithoutPrefix) {
				case "e":
					return world.getLoadedEntityList().toArray(new Entity[] {});
				case "p":
					{
						List<Entity> entities = world.getLoadedEntityList();
						ArrayList<Entity> players = new ArrayList<Entity>();
						for (Entity entity: entities) {
							if (entity instanceof EntityPlayer) players.add(entity);
						}
						if (players.isEmpty()) return new Entity[0];
						Entity nearestPlayer = null;
						double nearestDistance = Double.POSITIVE_INFINITY;
						for (Entity player: players) {
							double distance = player.getDistance(x, y, z);
							if (distance <= nearestDistance) {
								nearestPlayer = player;
								nearestDistance = distance;
							}
						}
						Entity[] out = new Entity[1];
						out[0] = nearestPlayer;
						return out;
					}
				case "r":
					{
						List<Entity> entities = world.getLoadedEntityList();
						ArrayList<Entity> players = new ArrayList<Entity>();
						for (Entity entity: entities) {
							if (entity instanceof EntityPlayer) players.add(entity);
						}
						if (players.isEmpty()) return new Entity[0];
						int index = world.rand.nextInt(players.size());
						Entity[] out = new Entity[1];
						out[0] = players.get(index);
						return out;
					}
				case "a":
					{
						List<Entity> entities = world.getLoadedEntityList();
						ArrayList<Entity> players = new ArrayList<Entity>();
						for (Entity entity: entities) {
							if (entity instanceof EntityPlayer) players.add(entity);
						}
						return players.toArray(new Entity[] {});
					}
				case "s":
					{
						if (executerEntity == null) return new Entity[0];
						Entity[] out = new Entity[1];
						out[0] = executerEntity;
						return out;
					}
				default:
					return null;
			}
		}
		if (token.startsWith("#")) {
			String tokenWithoutPrefix = token.substring(1);
			int entityInstanceId;
			try {
				entityInstanceId = Integer.parseInt(tokenWithoutPrefix);
			}
			catch (NumberFormatException e) {
				return null;
			}
			List<Entity> entityList = world.getLoadedEntityList();
			for (Entity entity: entityList) {
				if (entity.entityId == entityInstanceId) {
					Entity[] out = new Entity[1];
					out[0] = entity;
					return out;
				}
			}
			return null;
		}
		return null;
	}
}
