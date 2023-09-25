package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import com.swampmaster2160.morecommandsforreindev.mixininterfaces.IWorldInfo;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableLastTimePlayed extends WorldInfoVariableLong {
	@Override
	public long getValue(World world) {
		return world.getWorldInfo().getLastTimePlayed();
	}

	@Override
	public void setValue(World world, long value) {
		((IWorldInfo)world.worldInfo).setLastTimePlayed(value);
	}
}
