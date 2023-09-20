package com.swampmaster2160.morecommandsforreindev.commands;

import net.minecraft.mitask.PlayerCommandHandler;
import net.minecraft.mitask.command.Command;
import net.minecraft.mitask.command.CommandErrorHandler;
import net.minecraft.src.client.player.EntityPlayerSP;

public class CommandWorldInfo extends Command {
	public CommandWorldInfo(PlayerCommandHandler commandHandler) {
		super("worldinfo", true, false, new String[] {"wi"});
	}

	@Override
	public void onExecute(String[] args, EntityPlayerSP commandExecutor) {
		// If the command does not have 2 or 3 arguments, print the usage message.
		if (args.length != 2 && args.length != 3) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
	}

	@Override
	public void printHelpInformation(EntityPlayerSP var1) {
		// TODO
	}

	@Override
	public String commandSyntax() {
		return "\u00a7e/worldinfo <var> <value>";
	}
}
