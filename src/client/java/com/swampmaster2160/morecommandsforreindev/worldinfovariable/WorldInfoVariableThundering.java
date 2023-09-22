package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableThundering extends WorldInfoVariableBoolean {
	@Override
	public boolean getValue(World world) {
		return world.getWorldInfo().getThundering();
	}

	@Override
	public void setValue(World world, boolean value) {
		world.getWorldInfo().setThundering(value);
	}
}
