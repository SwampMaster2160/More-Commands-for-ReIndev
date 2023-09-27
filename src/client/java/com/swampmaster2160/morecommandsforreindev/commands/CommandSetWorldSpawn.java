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
		if (args.length != 4) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		if (args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty()) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		int x = 0;
		int y = 0;
		int z = 0;
		String xArg = args[1];
		String yArg = args[2];
		String zArg = args[3];
		if (xArg.startsWith("~")) {
			xArg = "0" + xArg.substring(1);
			x += commandExecutor.posX;
		}
		if (yArg.startsWith("~")) {
			yArg = "0" + yArg.substring(1);
			y += commandExecutor.posY;
		}
		if (zArg.startsWith("~")) {
			zArg = "0" + zArg.substring(1);
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
