package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableCheats extends WorldInfoVariableBoolean {
	@Override
	public boolean getValue(World world) {
		return world.getWorldInfo().isCheatsEnabled();
	}

	@Override
	public void setValue(World world, boolean value) {
		world.getWorldInfo().setCheatsBool(value);
	}
}
