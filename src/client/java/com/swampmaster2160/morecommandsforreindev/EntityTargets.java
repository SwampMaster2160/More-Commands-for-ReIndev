package com.swampmaster2160.morecommandsforreindev;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.entity.player.EntityPlayer;
import net.minecraft.src.game.level.World;

public abstract class EntityTargets {
	public static @Nullable Entity[] getTargetsFromString(World world, String string, double x, double y, double z, Entity executerEntity) {
		ArrayList<Object> tokensAndParsedObjects = new ArrayList<Object>();
		// Tokenise and first evaluation round
		@Nullable String currentToken = null;
		for (int index = 0; index < string.length(); index++) {
			char chr = string.charAt(index);
			if (chr == '(' || chr == ')' || chr == '!' || chr == '|' || chr == '&' || chr == '^') {
				tokensAndParsedObjects.add("" + chr);
				continue;
			}
			if (currentToken == null) currentToken = "";
			currentToken = currentToken + chr;
			@Nullable Character nextChar = null;
			if (index < string.length() - 1) nextChar = string.charAt(index + 1);
			if (nextChar == null || nextChar == '(' || nextChar == ')' || nextChar == '!' || nextChar == '|' || nextChar == '&' || nextChar == '^' || nextChar == '@' || nextChar == '#') {
				@Nullable Object tokenFirstRoundEvaluation = evaluateTokenFirstRound(world, currentToken, x, y, z, executerEntity);
				if (tokenFirstRoundEvaluation == null) return null;
				tokensAndParsedObjects.add(tokenFirstRoundEvaluation);
				currentToken = null;
			}
		}
		boolean hasError = evaluateTokens(world, tokensAndParsedObjects, 0, true);
		if (hasError) return null;
		return (Entity[])(tokensAndParsedObjects.get(0));

		/*for (Object token: tokensAndParsedObjects) {
			System.out.println(token);
		}*/

		//Entity[] out = new Entity[0];
		//return out;
		/*Object out = evaluateTokenFirstRound(world, string, x, y, z, executerEntity);
		if (out == null) return null;
		if (!(out instanceof Entity[])) return null;
		return (Entity[])out;*/
		//return null;
	}

	public static boolean evaluateTokens(World world, ArrayList<Object> tokens, int startIndex, boolean isRoot) {
		// Parse ()
		int parseEnd = 0;
		for (int index = startIndex; startIndex < tokens.size(); index++) {
			Object token = tokens.get(index);
			if (token instanceof String) {
				String tokenString = (String)token;
				if (tokenString.equals("(")) {
					tokens.remove(index);
					if (index == tokens.size()) return true;
					boolean hasError = evaluateTokens(world, tokens, index, false);
					if (hasError) return true;
					try {
						Object removed = tokens.remove(index + 1);
						if (!(removed instanceof String)) return true;
						if (!(((String)removed).equals(")"))) return true;
					}
					catch (IndexOutOfBoundsException e) {
						return true;
					}
				}
				if (tokenString.equals(")")) {
					if (isRoot) return true;
					parseEnd = index;
					break;
				}
			}
			if (index == tokens.size() - 1) {
				if (!isRoot) return true;
				parseEnd = index;
				break;
			}
		}
		// Parse !
		for (int index = parseEnd; index >= startIndex; index--) {
			Object token = tokens.get(index);
			if (token instanceof String) {
				String tokenString = (String)token;
				if (tokenString.equals("!")) {
					tokens.remove(index);
					Object nextToken = null;
					try {
						nextToken = tokens.get(index);
					}
					catch (IndexOutOfBoundsException e) {
						return true;
					}
					tokens.remove(index);
					if (!(nextToken instanceof Entity[])) return true;
					Entity[] nextTokenEntities = (Entity[])nextToken;
					List<Entity> allEntities = world.getLoadedEntityList();
					ArrayList<Entity> notResult = new ArrayList<Entity>();
					for (Entity entity: allEntities) {
						boolean hasEntity = true;
						for (Entity nextEntity: nextTokenEntities) {
							if (nextEntity == entity) {
								hasEntity = false;
								break;
							}
						}
						if (hasEntity) notResult.add(entity);
					}
					tokens.add(index, notResult.toArray(new Entity[] {}));
				}
			}
		}
		//
		return false;
	}

	public static @Nullable Object evaluateTokenFirstRound(World world, String token, double x, double y, double z, @Nullable Entity executerEntity) {
		if (token == "(" || token == ")" || token == "!" || token == "|" || token == "&" || token == "^") return token;
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
		List<Entity> entities = world.getLoadedEntityList();
		for (Entity entity: entities) {
			if (!(entity instanceof EntityPlayer)) continue;
			EntityPlayer player = (EntityPlayer)entity;
			if (!player.username.equals(token)) continue;
			Entity[] out = new Entity[1];
			out[0] = entity;
			return out;
		}
		return null;
	}
}
