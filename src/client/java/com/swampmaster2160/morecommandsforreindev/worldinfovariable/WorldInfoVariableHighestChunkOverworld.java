package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import com.swampmaster2160.morecommandsforreindev.mixininterfaces.IWorldInfo;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableHighestChunkOverworld extends WorldInfoVariableShort {
	@Override
	public short getValue(World world) {
		return world.getWorldInfo().getHighestChunkOW();
	}

	@Override
	public void setValue(World world, short value) {
		((IWorldInfo)world.worldInfo).setHighestChunkOW(value);
	}
}
