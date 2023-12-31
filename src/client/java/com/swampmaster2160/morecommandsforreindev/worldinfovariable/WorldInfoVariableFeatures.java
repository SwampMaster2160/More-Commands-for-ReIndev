package com.swampmaster2160.morecommandsforreindev.worldinfovariable;
import com.swampmaster2160.morecommandsforreindev.mixininterfaces.IWorld;
import com.swampmaster2160.morecommandsforreindev.mixininterfaces.IWorldInfo;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableFeatures extends WorldInfoVariableBoolean {
	@Override
	public boolean getValue(World world) {
		return world.getWorldInfo().isMapFeaturesEnabled();
	}

	@Override
	public void setValue(World world, boolean value) {
		((IWorldInfo)world.getWorldInfo()).setMapFeaturesEnabled(value);
		((IWorld)world).reCalculateChunkProvider();
	}
}
