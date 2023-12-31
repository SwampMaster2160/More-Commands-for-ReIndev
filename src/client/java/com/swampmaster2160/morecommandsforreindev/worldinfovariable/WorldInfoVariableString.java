package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import com.swampmaster2160.morecommandsforreindev.WorldInfoVariable;

import net.minecraft.src.game.level.World;

public abstract class WorldInfoVariableString extends WorldInfoVariable {
	@Override
	public String getValueAsString(World world) {
		return getValue(world);
	}

	@Override
	public boolean setValueAsString(World world, String[] valueWords) {
		if (valueWords.length > 1) return false;
		setValue(world, valueWords[0]);
		return true;
	}

	public abstract String getValue(World world);
	public abstract void setValue(World world, String value);
}
