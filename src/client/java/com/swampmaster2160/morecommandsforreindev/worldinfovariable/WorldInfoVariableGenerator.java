package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import com.swampmaster2160.morecommandsforreindev.mixininterfaces.IWorld;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableGenerator extends WorldInfoVariableInt {
	@Override
	public int getValue(World world) {
		return world.getWorldInfo().getGenType();
	}

	@Override
	public void setValue(World world, int value) {
		world.getWorldInfo().setGenType(value);
		((IWorld)world).reCalculateChunkProvider();
	}
}
