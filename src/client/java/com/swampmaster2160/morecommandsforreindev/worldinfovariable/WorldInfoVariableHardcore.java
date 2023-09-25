package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import com.swampmaster2160.morecommandsforreindev.mixininterfaces.IWorldInfo;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableHardcore extends WorldInfoVariableBoolean {
	@Override
	public boolean getValue(World world) {
		return world.getWorldInfo().isHardcoreModeEnabled();
	}

	@Override
	public void setValue(World world, boolean value) {
		((IWorldInfo)world.worldInfo).setHardcoreEnabled(value);
	}
}
