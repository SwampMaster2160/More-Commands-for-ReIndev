package com.swampmaster2160.morecommandsforreindev.commands;

import org.jetbrains.annotations.Nullable;

import com.swampmaster2160.morecommandsforreindev.WorldInfoVariable;

import net.minecraft.mitask.PlayerCommandHandler;
import net.minecraft.mitask.command.Command;
import net.minecraft.mitask.command.CommandErrorHandler;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.level.World;
import net.minecraft.src.game.stats.StatCollector;

public class CommandWorldInfo extends Command {
	public CommandWorldInfo(PlayerCommandHandler commandHandler) {
		super("worldinfo", true, false, new String[] {"wi"});
	}

	@Override
	public void onExecute(String[] args, EntityPlayerSP commandExecutor) {
		// If the command only has 1 argument, print the usage message.
		if (args.length < 2) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		String worldInfoVariableName = args[1];
		// If list or help was entered as a variable name
		if (worldInfoVariableName.equals("list") || worldInfoVariableName.equals("help")) {
			String messageHeader = StatCollector.translateToLocal("command.worldinfo.variable_list");
			commandExecutor.addChatMessage(messageHeader);
			String[] variableList = WorldInfoVariable.getDisplayNames();
			for (String variableName: variableList) {
				String message = StatCollector.translateToLocal("command.worldinfo.variable")
					.replace("%n", variableName);
				commandExecutor.addChatMessage(message);
			}
			return;
		}
		// Get the variable class
		@Nullable WorldInfoVariable worldInfoVariable = WorldInfoVariable.getVariable(worldInfoVariableName);
		// Print an error is there is no variable with that name
		if (worldInfoVariable == null) {
			String errorMessage = StatCollector.translateToLocal("command.worldinfo.invalid");
			commandExecutor.addChatMessage(errorMessage);
			return;
		}
		// If only 2 arguments are given, print the current value of the variable.
		if (args.length == 2) {
			String value = worldInfoVariable.getValueAsString(commandExecutor.worldObj);
			String message = StatCollector.translateToLocal("command.worldinfo.current_value")
				.replace("%v", value);
			commandExecutor.addChatMessage(message);
			return;
		}
		// Set the value of the variable
		World world = commandExecutor.worldObj;
		int newValueStringsCount = args.length - 2;
		String[] newValueStrings = new String[newValueStringsCount];
		for (int x = 0; x < newValueStringsCount; x++) {
			newValueStrings[x] = args[x + 2];
		}
		boolean isSuccessful = worldInfoVariable.setValueAsString(world, newValueStrings);
		// If failed then print the error messabe
		if (!isSuccessful) {
			String errorMessage = StatCollector.translateToLocal("command.worldinfo.invalid_value");
			commandExecutor.addChatMessage(errorMessage);
			return;
		}
		// Print success message
		String newValue = worldInfoVariable.getValueAsString(world);
		String message = StatCollector.translateToLocal("command.worldinfo.set")
			.replace("%n", worldInfoVariable.getDisplayName())
			.replace("%v", newValue);
		commandExecutor.addChatMessage(message);
	}

	@Override
	public void printHelpInformation(EntityPlayerSP var1) {
		// Prints usage and help by default
	}

	@Override
	public String commandSyntax() {
		return "\u00a7e/worldinfo <var|list> [value]";
	}
}
