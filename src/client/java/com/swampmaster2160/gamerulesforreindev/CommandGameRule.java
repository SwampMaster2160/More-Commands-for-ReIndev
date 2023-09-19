package com.swampmaster2160.gamerulesforreindev;

import org.jetbrains.annotations.Nullable;

import net.minecraft.mitask.PlayerCommandHandler;
import net.minecraft.mitask.command.Command;
import net.minecraft.mitask.command.CommandErrorHandler;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.stats.StatCollector;

public class CommandGameRule extends Command {
	public CommandGameRule(PlayerCommandHandler commandHandler) {
		super("gamerule", true, false, new String[] {"gr"});
	}

	@Override
	public void onExecute(String[] args, EntityPlayerSP commandExecutor) {
		if (args.length != 3 && args.length != 2) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		String gameRuleName = args[1];
		if (!GameRule.doesGameruleExist(gameRuleName)) {
			String invalidGameruleText = "\u00A7c" + StatCollector.translateToLocal("command.gamerule.invalid") + ": ";
			commandExecutor.worldObj.playSoundAtEntity(commandExecutor, "random.wood click", 0.8F, 1.0F);
			commandExecutor.addChatMessage(invalidGameruleText + gameRuleName);
			return;
		}
		WorldGameRules worldGameRules = ((IWorldInfo)(Object)commandExecutor.worldObj.getWorldInfo()).getGameRules();
		if (args.length == 2) {
			String currentValueText = "\u00a7a" + StatCollector.translateToLocal("command.gamerule.current_value") + ": " + "\u00a7e";
			commandExecutor.addChatMessage(currentValueText + worldGameRules.getGameRuleValue(gameRuleName));
			return;
		}
		String newValue = args[2];
		GameRule gameRule = GameRule.getRegisteredGameRule(gameRuleName);
		@Nullable Object newValueParsed = gameRule.parseCommandString(newValue);
		if (newValueParsed == null) {
			String invalidGameruleText = "\u00A7c" + StatCollector.translateToLocal("command.gamerule.invalid_value") + ": ";
			commandExecutor.worldObj.playSoundAtEntity(commandExecutor, "random.wood click", 0.8F, 1.0F);
			commandExecutor.addChatMessage(invalidGameruleText + newValue);
			return;
		}
		worldGameRules.setGameRuleValue(gameRuleName, newValueParsed);
		String gameRuleSetText = StatCollector.translateToLocal("command.gamerule.set")
			.replace("%g", gameRuleName)
			.replace("%v", "" + newValueParsed);
		commandExecutor.addChatMessage(gameRuleSetText);
	}

	@Override
	public void printHelpInformation(EntityPlayerSP var1) {
		// TODO
	}

	@Override
	public String commandSyntax() {
		return "\u00a7e/gamerule <gamerule> <value>";
	}
}
