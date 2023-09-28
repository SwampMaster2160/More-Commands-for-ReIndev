package com.swampmaster2160.morecommandsforreindev.commands;

import net.minecraft.mitask.PlayerCommandHandler;
import net.minecraft.mitask.command.Command;
import net.minecraft.mitask.command.CommandErrorHandler;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.level.World;
import net.minecraft.src.game.level.chunk.ChunkCoordinates;
import net.minecraft.src.game.stats.StatCollector;

public class CommandSetWorldSpawn extends Command {
	public CommandSetWorldSpawn(PlayerCommandHandler commandHandler) {
		super("setworldspawn", true, false, new String[] {"sws"});
	}

	@Override
	public void onExecute(String[] args, EntityPlayerSP commandExecutor) {
		if (args.length != 4 && args.length != 1  && args.length != 3) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		String xArg = "~";
		String yArg = "~";
		String zArg = "~";
		if (args.length == 3) {
			xArg = args[1];
			zArg = args[2];
		}
		if (args.length == 4) {
			xArg = args[1];
			yArg = args[2];
			zArg = args[3];
		}
		if (xArg.isEmpty() || yArg.isEmpty() || zArg.isEmpty()) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		int x = 0;
		int y = 0;
		int z = 0;
		if (xArg.startsWith("~")) {
			xArg = xArg.substring(1);
			if (xArg.isEmpty()) {
				xArg = "0";
			}
			x += commandExecutor.posX;
		}
		if (yArg.startsWith("~")) {
			yArg = yArg.substring(1);
			if (yArg.isEmpty()) {
				yArg = "0";
			}
			y += commandExecutor.posY;
		}
		if (zArg.startsWith("~")) {
			zArg = zArg.substring(1);
			if (zArg.isEmpty()) {
				zArg = "0";
			}
			z += commandExecutor.posZ;
		}
		try {
			x += Integer.parseInt(xArg);
			y += Integer.parseInt(yArg);
			z += Integer.parseInt(zArg);
		}
		catch (NumberFormatException e) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		World world = commandExecutor.worldObj;
		world.setSpawnPoint(new ChunkCoordinates(x, y, z));
		ChunkCoordinates spawnPoint = world.getSpawnPoint();
		String message = StatCollector.translateToLocal("command.setworldspawn.set")
			.replace("%x", "" + spawnPoint.x)
			.replace("%y", "" + spawnPoint.y)
			.replace("%z", "" + spawnPoint.z);
		commandExecutor.addChatMessage(message);
	}

	@Override
	public void printHelpInformation(EntityPlayerSP var1) {
		
	}

	@Override
	public String commandSyntax() {
		return "\u00a7e/setworldspawn <x> <y> <z>";
	}
}
