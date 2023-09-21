package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import com.swampmaster2160.morecommandsforreindev.mixininterfaces.IWorld;
import com.swampmaster2160.morecommandsforreindev.mixininterfaces.IWorldInfo;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableSeed extends WorldInfoVariableLong {

	@Override
	public long getValue(World world) {
		return world.getWorldSeed();
	}

	@Override
	public void setValue(World world, long value) {
		((IWorldInfo)world.worldInfo).setRandomSeed(value);
		((IWorld)world).reCalculateChunkProvider();
	}
}
