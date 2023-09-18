package com.swampmaster2160.gamerulesforreindev;

import net.minecraft.mitask.PlayerCommandHandler;
import net.minecraft.mitask.command.Command;
import net.minecraft.src.client.player.EntityPlayerSP;

public class CommandGameRule extends Command {
	public CommandGameRule(PlayerCommandHandler commandHandler) {
		super("gamerule", true, false, null);
	}

	@Override
	public void printHelpInformation(EntityPlayerSP var1) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'printHelpInformation'");
	}

	@Override
	public String commandSyntax() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'commandSyntax'");
	}
}
