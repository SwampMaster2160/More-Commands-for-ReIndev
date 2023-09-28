package com.swampmaster2160.morecommandsforreindev.commands;

import net.minecraft.mitask.PlayerCommandHandler;
import net.minecraft.mitask.command.Command;
import net.minecraft.mitask.command.CommandErrorHandler;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.stats.StatCollector;

public class CommandSeed extends Command {
	public CommandSeed(PlayerCommandHandler commandHandler) {
		super("seed", false, false, (String[])null);
	}

	@Override
	public void onExecute(String[] args, EntityPlayerSP commandExecutor) {
		// The command should not have any arguments
		if (args.length != 1) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		// Get and print seed
		long seed = commandExecutor.worldObj.getWorldSeed();
		String message = StatCollector.translateToLocal("command.seed.value")
			.replace("%v", "" + seed);
		commandExecutor.addChatMessage(message);
	}

	@Override
	public void printHelpInformation(EntityPlayerSP var1) {
		// Prints usage and help by default
	}

	@Override
	public String commandSyntax() {
		return "\u00a7e/seed";
	}
}
