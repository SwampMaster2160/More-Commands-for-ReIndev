package com.swampmaster2160.morecommandsforreindev;

import com.fox2code.foxloader.loader.ClientMod;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableDimension;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableFeatures;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableGenerator;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableSeed;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableSizeOnDisk;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableSpawnX;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableSpawnY;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableSpawnZ;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableWorldTime;

public class MoreCommandsForReIndevClient extends MoreCommandsForReIndev implements ClientMod {
	@Override
	public void onInit() {
		// Client specific code

		// Register world info variables
		WorldInfoVariable.registerVariable("seed", new WorldInfoVariableSeed(), new String[] {"s"});
		WorldInfoVariable.registerVariable("generator", new WorldInfoVariableGenerator(), new String[] {"g"});
		WorldInfoVariable.registerVariable("features", new WorldInfoVariableFeatures(), new String[] {"f"});
		WorldInfoVariable.registerVariable("spawnX", new WorldInfoVariableSpawnX(), new String[] {"sx"});
		WorldInfoVariable.registerVariable("spawnY", new WorldInfoVariableSpawnY(), new String[] {"sy"});
		WorldInfoVariable.registerVariable("spawnZ", new WorldInfoVariableSpawnZ(), new String[] {"sz"});
		WorldInfoVariable.registerVariable("worldTime", new WorldInfoVariableWorldTime(), new String[] {"wt"});
		WorldInfoVariable.registerVariable("sizeOnDisk", new WorldInfoVariableSizeOnDisk(), new String[] {"sd"});
		WorldInfoVariable.registerVariable("dimension", new WorldInfoVariableDimension(), new String[] {"d"});
	}
}
