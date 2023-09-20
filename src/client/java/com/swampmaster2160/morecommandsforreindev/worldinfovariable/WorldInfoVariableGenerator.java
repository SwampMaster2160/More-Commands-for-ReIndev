package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import com.swampmaster2160.morecommandsforreindev.IWorld;
import com.swampmaster2160.morecommandsforreindev.WorldInfoVariable;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableGenerator extends WorldInfoVariable {
	@Override
	public String getValueAsString(World world) {
		return "" + world.getWorldInfo().getGenType();
	}

	@Override
	public boolean setValueAsString(World world, String value) {
		// Get generator type from string
		int generatorType = 0;
		try {
			generatorType = Integer.parseInt(value);
		}
		catch (NumberFormatException e) {
			return false;
		}
		// Set generator type
		world.getWorldInfo().setGenType(generatorType);
		((IWorld)world).reCalculateChunkProvider();
		return true;
	}
}
