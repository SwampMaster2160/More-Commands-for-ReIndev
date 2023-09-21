package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import com.swampmaster2160.morecommandsforreindev.mixininterfaces.IWorld;
import com.swampmaster2160.morecommandsforreindev.mixininterfaces.IWorldInfo;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableDimension extends WorldInfoVariableInt {
	@Override
	public int getValue(World world) {
		return world.getWorldInfo().getDimension();
	}

	@Override
	public void setValue(World world, int value) {
		((IWorldInfo)world.worldInfo).setDimension(value);
		((IWorld)world).reCalculateChunkProvider();
	}
}
