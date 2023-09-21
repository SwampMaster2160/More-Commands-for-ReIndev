package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableSpawnX extends WorldInfoVariableInt {
	@Override
	public int getValue(World world) {
		return world.getWorldInfo().getSpawnX();
	}

	@Override
	public void setValue(World world, int value) {
		world.getWorldInfo().setSpawnX(value);
	}
}
