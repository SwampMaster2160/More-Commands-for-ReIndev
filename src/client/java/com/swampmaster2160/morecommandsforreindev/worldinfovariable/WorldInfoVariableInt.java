package com.swampmaster2160.morecommandsforreindev.worldinfovariable;
import com.swampmaster2160.morecommandsforreindev.WorldInfoVariable;

import net.minecraft.src.game.level.World;

public abstract class WorldInfoVariableInt extends WorldInfoVariable {
	@Override
	public String getValueAsString(World world) {
		return "" + getValue(world);
	}

	@Override
	public boolean setValueAsString(World world, String[] valueWords) {
		// Get value from string
		if (valueWords.length > 1) return false;
		int intValue = 0;
		try {
			intValue = Integer.parseInt(valueWords[0]);
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
