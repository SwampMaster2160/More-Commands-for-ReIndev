package com.swampmaster2160.morecommandsforreindev;

import com.fox2code.foxloader.loader.ClientMod;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableSeed;

public class MoreCommandsForReIndevClient extends MoreCommandsForReIndev implements ClientMod {
	@Override
	public void onInit() {
		// Client specific code

		// Register world info variables
		WorldInfoVariable.registerVariable("seed", new WorldInfoVariableSeed(), new String[] {"s"});
	}
}
