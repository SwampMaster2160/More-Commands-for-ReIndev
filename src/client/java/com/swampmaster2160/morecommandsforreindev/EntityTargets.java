package com.swampmaster2160.morecommandsforreindev;

import org.jetbrains.annotations.Nullable;

import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.level.World;

public abstract class EntityTargets {
	public static @Nullable Entity[] getTargetsFromString(World world, String string, double x, double y, double z) {
		//Entity[] out = new Entity[0];
		//return out;
		return evaluateTokenFirstRound(world, string, x, y, z);
	}

	public static @Nullable Entity[] evaluateTokenFirstRound(World world, String token, double x, double y, double z) {
		if (token.startsWith("@")) {
			String tokenWithoutPrefix = token.substring(1);
			switch (tokenWithoutPrefix) {
				case "e":
					return world.getLoadedEntityList().toArray(new Entity[] {});
				/*case "p":
					Entity[] out = new Entity[1];
					out[0] = world.getClosestPlayer(x, y, z, 0D);
					return out;*/
				default:
					return null;
			}
		}
		return null;
	}
}
