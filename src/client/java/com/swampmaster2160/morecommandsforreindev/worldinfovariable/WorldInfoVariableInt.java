package com.swampmaster2160.morecommandsforreindev.worldinfovariable;
import com.swampmaster2160.morecommandsforreindev.WorldInfoVariable;

import net.minecraft.src.game.level.World;

public abstract class WorldInfoVariableInt extends WorldInfoVariable {
	@Override
	public String getValueAsString(World world) {
		return "" + getValue(world);
	}

	@Override
	public boolean setValueAsString(World world, String value) {
		// Get value from string
		int intValue = 0;
		try {
			intValue = Integer.parseInt(value);
		}
		catch (NumberFormatException e) {
			return false;
		}
		// Set value
		setValue(world, intValue);
		return true;
	}

	public abstract int getValue(World world);
	public abstract void setValue(World world, int value);
}
