package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import com.swampmaster2160.morecommandsforreindev.WorldInfoVariable;

import net.minecraft.src.game.level.World;

public abstract class WorldInfoVariableShort extends WorldInfoVariable {
	@Override
	public String getValueAsString(World world) {
		return "" + getValue(world);
	}

	@Override
	public boolean setValueAsString(World world, String[] valueWords) {
		// Get value from string
		if (valueWords.length > 1) return false;
		short shortValue = 0;
		try {
			shortValue = Short.parseShort(valueWords[0]);
		}
		catch (NumberFormatException e) {
			return false;
		}
		// Set value
		setValue(world, shortValue);
		return true;
	}

	public abstract short getValue(World world);
	public abstract void setValue(World world, short value);
}
