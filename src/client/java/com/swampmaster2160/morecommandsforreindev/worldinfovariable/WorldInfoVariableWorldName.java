package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableWorldName extends WorldInfoVariableString {
	@Override
	public String getValue(World world) {
		return world.getWorldInfo().getWorldName();
	}

	@Override
	public void setValue(World world, String value) {
		world.getWorldInfo().setWorldName(value);
	}
}
