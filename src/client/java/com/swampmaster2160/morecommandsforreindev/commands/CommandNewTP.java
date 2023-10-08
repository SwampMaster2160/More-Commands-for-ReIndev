package com.swampmaster2160.morecommandsforreindev.commands;

import net.minecraft.mitask.PlayerCommandHandler;
import net.minecraft.mitask.command.Command;
import net.minecraft.mitask.command.CommandErrorHandler;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.stats.StatCollector;

public class CommandNewTP extends Command {
	public CommandNewTP(PlayerCommandHandler commandHandler) {
		super("tp", true, false, (String[])null);
	}

	@Override
	public void onExecute(String[] args, EntityPlayerSP commandExecutor) {
		if (args.length != 4 && args.length != 5) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		if (!args[1].startsWith("~") && !args[2].startsWith("~") && !args[3].startsWith("~")) {
			this.handleTeleport(args, commandExecutor, false);
		}
		else {
			this.handleTeleport(args, commandExecutor, true);
		}
	}

	private void handleTeleport(String[] args, EntityPlayerSP commandExecutor, boolean bool) {
		String tpTo = StatCollector.translateToLocal("command.tp.teleport_to_coords");
		String tpFrom = StatCollector.translateToLocal("command.tp.previously_at");

		try {
			if (bool) {
				double x = Double.parseDouble(args[1].replaceAll("~", ""));
				double y = Double.parseDouble(args[2].replaceAll("~", ""));
				double z = Double.parseDouble(args[3].replaceAll("~", ""));
				if (args[1].startsWith("~")) {
					x += commandExecutor.posX;
				}

				if (args[2].startsWith("~")) {
					y += commandExecutor.posY;
				}

				if (args[3].startsWith("~")) {
					z += commandExecutor.posZ;
				}

				double xPrev = commandExecutor.prevPosX;
				double yPrev = commandExecutor.prevPosY;
				double zPrev = commandExecutor.prevPosZ;
				commandExecutor.setPosition(x, y, z);
				commandExecutor.addChatMessage(
					tpTo
						+ " "
						+ "\u00a7c"
						+ "x: "
						+ "\u00a7r"
						+ x
						+ ", "
						+ "\u00a7a"
						+ "y: "
						+ "\u00a7r"
						+ y
						+ ", "
						+ "\u00a79"
						+ "z: \u00ef\u00bf\u00bdr"
						+ z
						+ "."
				);
				commandExecutor.addChatMessage(
					"\u00a77("
						+ tpFrom
						+ " "
						+ "\u00a7c"
						+ "x: "
						+ "\u00a77"
						+ xPrev
						+ ", "
						+ "\u00a7a"
						+ "y: "
						+ "\u00a77"
						+ yPrev
						+ ", "
						+ "\u00a79"
						+ "z: "
						+ "\u00a77"
						+ zPrev
						+ ")"
				);
			} else {
				double x = Double.parseDouble(args[1]);
				double y = Double.parseDouble(args[2]);
				double z = Double.parseDouble(args[3]);
				double xPrev = commandExecutor.prevPosX;
				double yPrev = commandExecutor.prevPosY;
				double zPrev = commandExecutor.prevPosZ;
				commandExecutor.setPosition(x, y, z);
				commandExecutor.addChatMessage(
					tpTo
						+ " "
						+ "\u00a7c"
						+ "x: "
						+ "\u00a7r"
						+ x
						+ ", "
						+ "\u00a7a"
						+ "y: "
						+ "\u00a7r"
						+ y
						+ ", "
						+ "\u00a79"
						+ "z: "
						+ "\u00a7r"
						+ z
						+ "."
				);
				commandExecutor.addChatMessage(
					"\u00a77("
						+ tpFrom
						+ " "
						+ "\u00a7c"
						+ "x: "
						+ "\u00a77"
						+ xPrev
						+ ", "
						+ "\u00a7a"
						+ "y: "
						+ "\u00a77"
						+ yPrev
						+ ", "
						+ "\u00a79"
						+ "z: "
						+ "\u00a77"
						+ zPrev
						+ ")"
				);
			}
		} catch (NumberFormatException var18) {
			commandExecutor.addChatMessage(CommandErrorHandler.invalidTpCoords);
		}

	}

	@Override
	public void printHelpInformation(EntityPlayerSP commandExecutor) {
	}

	@Override
	public String commandSyntax() {
		return "\u00a7e/tp <x> <y> <z> test";
	}
}
