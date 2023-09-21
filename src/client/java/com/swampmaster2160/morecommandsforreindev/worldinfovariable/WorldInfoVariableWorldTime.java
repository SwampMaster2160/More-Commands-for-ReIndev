package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableWorldTime extends WorldInfoVariableLong {
	@Override
	public long getValue(World world) {
		return world.getWorldTime();
	}

	@Override
	public void setValue(World world, long value) {
		world.setWorldTime(value);
	}
}
