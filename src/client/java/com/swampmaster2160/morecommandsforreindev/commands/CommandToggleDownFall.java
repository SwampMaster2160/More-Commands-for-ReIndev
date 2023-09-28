package com.swampmaster2160.morecommandsforreindev.commands;

import net.minecraft.mitask.PlayerCommandHandler;
import net.minecraft.mitask.command.Command;
import net.minecraft.mitask.command.CommandErrorHandler;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.level.WorldInfo;
import net.minecraft.src.game.stats.StatCollector;

public class CommandToggleDownFall extends Command {
	public CommandToggleDownFall(PlayerCommandHandler commandHandler) {
		super("toggledownfall", true, false, new String[] { "tdf" });
	}

	@Override
	public void onExecute(String[] args, EntityPlayerSP commandExecutor) {
		// The command should not have any arguments
		if (args.length != 1) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		// Toggle downfall
		WorldInfo worldInfo = commandExecutor.worldObj.getWorldInfo();
		worldInfo.setRaining(!worldInfo.getRaining());
		String message = StatCollector.translateToLocal("command.toggledownfall.toggle");
		commandExecutor.addChatMessage(message);
	}

	@Override
	public void printHelpInformation(EntityPlayerSP var1) {
		// Prints usage and help by default
	}

	@Override
	public String commandSyntax() {
		return "\u00a7e/toggledownfall";
	}
}
