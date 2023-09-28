package com.swampmaster2160.morecommandsforreindev.commands;

import org.jetbrains.annotations.Nullable;

import com.swampmaster2160.morecommandsforreindev.MoreCommandsForReIndevClient;

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
		// Command should have 1 or 4 arguments including the command name
		if (args.length != 1 && args.length != 4) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		// Get x, y and z arguments which deafult to "~" if the command format does not have the argument
		String xArg = "~";
		String yArg = "~";
		String zArg = "~";
		if (args.length == 4) {
			xArg = args[1];
			yArg = args[2];
			zArg = args[3];
		}
		// Parse x, y and z arguments
		@Nullable Integer x = MoreCommandsForReIndevClient.parseIntCoordinate(xArg, (int)Math.round(commandExecutor.posX));
		@Nullable Integer y = MoreCommandsForReIndevClient.parseIntCoordinate(yArg, (int)Math.round(commandExecutor.posY));
		@Nullable Integer z = MoreCommandsForReIndevClient.parseIntCoordinate(zArg, (int)Math.round(commandExecutor.posZ));
		if (x == null || y == null || z == null) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		// Set spawn point
		World world = commandExecutor.worldObj;
		world.setSpawnPoint(new ChunkCoordinates(x, y, z));
		// Print success message
		ChunkCoordinates spawnPoint = world.getSpawnPoint();
		String message = StatCollector.translateToLocal("command.setworldspawn.set")
			.replace("%x", "" + spawnPoint.x)
			.replace("%y", "" + spawnPoint.y)
			.replace("%z", "" + spawnPoint.z);
		commandExecutor.addChatMessage(message);
	}

	@Override
	public void printHelpInformation(EntityPlayerSP var1) {
		// Prints usage and help by default
	}

	@Override
	public String commandSyntax() {
		return "\u00a7e/setworldspawn [x y z]";
	}
}
