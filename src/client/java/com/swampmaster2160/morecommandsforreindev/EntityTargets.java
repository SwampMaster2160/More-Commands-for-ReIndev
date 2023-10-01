package com.swampmaster2160.morecommandsforreindev;

import org.jetbrains.annotations.Nullable;

import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.level.World;

public abstract class EntityTargets {
	public static @Nullable Entity[] getTargetsFromString(World world, String string) {
		Entity[] out = new Entity[0];
		return out;
	}
}
