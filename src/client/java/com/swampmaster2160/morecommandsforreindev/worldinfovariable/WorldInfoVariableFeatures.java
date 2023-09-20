package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import com.swampmaster2160.morecommandsforreindev.IWorld;
import com.swampmaster2160.morecommandsforreindev.IWorldInfo;
import com.swampmaster2160.morecommandsforreindev.WorldInfoVariable;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableFeatures extends WorldInfoVariable {
	@Override
	public String getValueAsString(World world) {
		return "" + world.getWorldInfo().isMapFeaturesEnabled();
	}

	@Override
	public boolean setValueAsString(World world, String value) {
		// Get generator type from string
		boolean isMapFeaturesEnabled = false;
		try {
			isMapFeaturesEnabled = Boolean.parseBoolean(value);
		}
		catch (NumberFormatException e) {
			return false;
		}
		// Set generator type
		((IWorldInfo)world.getWorldInfo()).setMapFeaturesEnabled(isMapFeaturesEnabled);
		((IWorld)world).reCalculateChunkProvider();
		return true;
	}
}
