package com.swampmaster2160.morecommandsforreindev.worldinfovariable;

import com.swampmaster2160.morecommandsforreindev.WorldInfoVariable;

import net.minecraft.src.game.level.World;
import net.minecraft.src.game.level.WorldInfo;

public class WorldInfoVariableSpawnPos extends WorldInfoVariable {
	@Override
	public String getValueAsString(World world) {
		WorldInfo worldInfo = world.getWorldInfo();
		return worldInfo.getSpawnX() + " " + worldInfo.getSpawnY() + " " + worldInfo.getSpawnZ();
	}

	@Override
	public boolean setValueAsString(World world, String[] valueWords) {
		// Get x, y and z
		if (valueWords.length != 3) return false;
		int x = 0;
		int y = 0;
		int z = 0;
		try {
			x = Integer.parseInt(valueWords[0]);
			y = Integer.parseInt(valueWords[1]);
			z = Integer.parseInt(valueWords[2]);
		}
		catch (NumberFormatException e) {
			return false;
		}
		// Set spawn pos
		world.getWorldInfo().setSpawn(x, y, z);
		return true;
	}
}
