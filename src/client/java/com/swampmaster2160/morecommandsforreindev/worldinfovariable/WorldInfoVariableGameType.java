package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableGameType extends WorldInfoVariableInt {
	@Override
	public int getValue(World world) {
		return world.getWorldInfo().getGameType();
	}

	@Override
	public void setValue(World world, int value) {
		world.getWorldInfo().setGameType(value);
	}
}
