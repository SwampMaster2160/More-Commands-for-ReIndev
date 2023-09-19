package com.swampmaster2160.morecommandsforreindev.commands;

import net.minecraft.mitask.PlayerCommandHandler;
import net.minecraft.mitask.command.Command;
import net.minecraft.src.client.player.EntityPlayerSP;

public class CommandWorldInfo extends Command {
	public CommandWorldInfo(PlayerCommandHandler commandHandler) {
		super("worldinfo", true, false, new String[] {"wi"});
	}

	@Override
	public void onExecute(String[] args, EntityPlayerSP commandExecutor) {
		
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
