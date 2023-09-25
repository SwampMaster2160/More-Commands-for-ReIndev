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
		// Get the variable class
		String worldInfoVariableName = args[1];
		@Nullable WorldInfoVariable worldInfoVariable = WorldInfoVariable.getVariable(worldInfoVariableName);
		// Print an error is there is no variable with that name
		if (worldInfoVariable == null) {
			String errorText = "\u00a7c" + StatCollector.translateToLocal("command.worldinfo.invalid");
			commandExecutor.addChatMessage(errorText);
			return;
		}
		// If only 2 arguments are given, print the current value of the variable.
		if (args.length == 2) {
			String value = worldInfoVariable.getValueAsString(commandExecutor.worldObj);
			String output = "\u00a7a" + StatCollector.translateToLocal("command.worldinfo.current_value") + ": " + "\u00a7e" +  value;
			commandExecutor.addChatMessage(output);
			return;
		}
		// Set the value of the variable
		//String newValueString = args[2];
		World world = commandExecutor.worldObj;
		int newValueStringsCount = args.length - 2;
		String[] newValueStrings = new String[newValueStringsCount];
		for (int x = 0; x < newValueStringsCount; x++) {
			newValueStrings[x] = args[x + 2];
		}
		boolean isSuccessful = worldInfoVariable.setValueAsString(world, newValueStrings);
		// If failed then print the error messabe
		if (!isSuccessful) {
			String errorText = "\u00a7c" + StatCollector.translateToLocal("command.worldinfo.invalid_value");
			commandExecutor.addChatMessage(errorText);
			return;
		}
		// Print success message
		String newValue = worldInfoVariable.getValueAsString(world);
		String output = StatCollector.translateToLocal("command.worldinfo.set")
			.replace("%n", worldInfoVariable.displayName)
			.replace("%v", newValue);
		commandExecutor.addChatMessage(output);
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
