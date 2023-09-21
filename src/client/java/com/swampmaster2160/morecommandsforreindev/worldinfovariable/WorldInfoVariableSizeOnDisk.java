package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableSizeOnDisk extends WorldInfoVariableLong {
	@Override
	public long getValue(World world) {
		return world.getWorldInfo().getSizeOnDisk();
	}

	@Override
	public void setValue(World world, long value) {
		world.getWorldInfo().setSizeOnDisk(value);
	}
}
