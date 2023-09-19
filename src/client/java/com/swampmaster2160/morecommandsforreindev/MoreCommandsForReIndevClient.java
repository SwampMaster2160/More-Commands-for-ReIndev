package com.swampmaster2160.morecommandsforreindev;

import com.fox2code.foxloader.loader.ClientMod;

public class MoreCommandsForReIndevClient extends MoreCommandsForReIndev implements ClientMod {
	@Override
	public void onInit() {
		// Client specific code

		// Register game rules
		//GameRule.registerGameRule("doDayNightCycle", new GameRuleDoDayNightCycle());
	}
}
