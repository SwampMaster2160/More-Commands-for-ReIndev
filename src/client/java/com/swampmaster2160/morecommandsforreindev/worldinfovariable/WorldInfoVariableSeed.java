package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import com.swampmaster2160.morecommandsforreindev.IWorld;
import com.swampmaster2160.morecommandsforreindev.IWorldInfo;
import com.swampmaster2160.morecommandsforreindev.WorldInfoVariable;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableSeed extends WorldInfoVariable {
	@Override
	public String getValueAsString(World world) {
		return "" + world.getWorldSeed();
	}

	@Override
	public boolean setValueAsString(World world, String value) {
		// Get seed from string
		long seed = 0;
		try {
			seed = Long.parseLong(value);
		}
		catch (NumberFormatException e) {
			return false;
		}
		// Set seed
		IWorldInfo worldInfo = ((IWorldInfo)world.worldInfo);
		worldInfo.setRandomSeed(seed);
		((IWorld)world).reCalculateChunkProvider();
		return true;
	}
}
