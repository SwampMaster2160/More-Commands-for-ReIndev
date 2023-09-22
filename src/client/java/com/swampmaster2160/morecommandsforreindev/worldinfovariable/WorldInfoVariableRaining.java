package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableRaining extends WorldInfoVariableBoolean {
	@Override
	public boolean getValue(World world) {
		return world.getWorldInfo().getRaining();
	}

	@Override
	public void setValue(World world, boolean value) {
		world.getWorldInfo().setRaining(value);
	}
}
