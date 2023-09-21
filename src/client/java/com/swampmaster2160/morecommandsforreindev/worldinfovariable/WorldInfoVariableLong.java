package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import com.swampmaster2160.morecommandsforreindev.WorldInfoVariable;

import net.minecraft.src.game.level.World;

public abstract class WorldInfoVariableLong extends WorldInfoVariable {
	@Override
	public String getValueAsString(World world) {
		return "" + getValue(world);
	}

	@Override
	public boolean setValueAsString(World world, String value) {
		// Get value from string
		long longValue = 0;
		try {
			longValue = Long.parseLong(value);
		}
		catch (NumberFormatException e) {
			return false;
		}
		// Set value
		setValue(world, longValue);
		return true;
	}

	public abstract long getValue(World world);
	public abstract void setValue(World world, long value);
}
