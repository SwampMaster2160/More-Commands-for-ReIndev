package com.swampmaster2160.gamerulesforreindev;

import com.fox2code.foxloader.loader.ClientMod;

public class GameRulesForReIndevClient extends GameRulesForReIndev implements ClientMod {
	@Override
	public void onInit() {
		// Client specific code

		// Register commands
		//PlayerCommandHandler.commands.add(new GameRuleCommand(PlayerCommandHandler.instance));
	}

	/*@Override
	public void onPreInit() {
		// TODO Auto-generated method stub
		//super.onPreInit();
		PlayerCommandHandler.commands.add(new GameRuleCommand(PlayerCommandHandler.instance));
	}*/

	
}
