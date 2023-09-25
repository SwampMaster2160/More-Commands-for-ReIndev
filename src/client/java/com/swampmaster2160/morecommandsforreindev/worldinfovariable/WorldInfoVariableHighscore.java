package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import net.minecraft.src.game.level.World;

public class WorldInfoVariableHighscore extends WorldInfoVariableLong {
	@Override
	public long getValue(World world) {
		return world.getWorldInfo().getHighScore();
	}

	@Override
	public void setValue(World world, long value) {
		world.getWorldInfo().setHighScore(value);
	}
}
