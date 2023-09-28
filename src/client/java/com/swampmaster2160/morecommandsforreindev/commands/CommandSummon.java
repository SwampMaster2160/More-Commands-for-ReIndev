package com.swampmaster2160.morecommandsforreindev.commands;

import java.util.ArrayList;

import org.jetbrains.annotations.Nullable;

import com.swampmaster2160.morecommandsforreindev.MoreCommandsForReIndevClient;

import net.minecraft.mitask.PlayerCommandHandler;
import net.minecraft.mitask.command.Command;
import net.minecraft.mitask.command.CommandErrorHandler;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.entity.EntityList;
import net.minecraft.src.game.level.World;
import net.minecraft.src.game.stats.StatCollector;

public class CommandSummon extends Command {
	private static ArrayList<Integer> badEntitityIds = new ArrayList<Integer>();
	private static ArrayList<String> badEntitityNames = new ArrayList<String>();

	public CommandSummon(PlayerCommandHandler commandHandler) {
		super("summon", true, false, (String[])null);
	}

	/**
	 * Add an entity that cannot be summoned
	 * @param id The entity id
	 * @param name The entity name
	 */
	public static void addBadEnitity(int id, String name) {
		badEntitityIds.add(id);
		badEntitityNames.add(name);
	}

	@Override
	public void onExecute(String[] args, EntityPlayerSP commandExecutor) {
		// The command must be 2 or 5 arguments long
		if (args.length != 2 && args.length != 5) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		// Get x, y and z arguments which deafult to "~" if the command format does not have the argument
		String entityNameOrId = args[1];
		String xArg = "~";
		String yArg = "~";
		String zArg = "~";
		if (args.length == 5) {
			xArg = args[2];
			yArg = args[3];
			zArg = args[4];
		}
		// Parse x, y and z arguments
		@Nullable Double x = MoreCommandsForReIndevClient.parseDoubleCoordinate(xArg, commandExecutor.posX);
		@Nullable Double y = MoreCommandsForReIndevClient.parseDoubleCoordinate(yArg, commandExecutor.posY);
		@Nullable Double z = MoreCommandsForReIndevClient.parseDoubleCoordinate(zArg, commandExecutor.posZ);
		if (x == null || y == null || z == null) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		// Create entity object
		World world = commandExecutor.worldObj;
		@Nullable Entity entity = null;
		try {
			int entityId = Integer.parseInt(entityNameOrId);
			if (badEntitityIds.contains(entityId)) {
				entityId = -1;
			}
			entity = EntityList.createEntity(entityId, world);
		}
		catch (NumberFormatException e) {
			if (badEntitityNames.contains(entityNameOrId)) {
				entityNameOrId = "";
			}
			entity = EntityList.createEntityInWorld(entityNameOrId, world);
		}
		// Print error if there was a problem creating the entity
		if (entity == null) {
			String message = StatCollector.translateToLocal("command.summon.unable_to_summon");
			commandExecutor.addChatMessage(message);
			return;
		}
		// Set entity position and add it to the world
		entity.setPosition(x, y, z);
		world.entityJoinedWorld(entity);
		// Print success message
		String message = StatCollector.translateToLocal("command.summon.summon")
			.replace("%n", "" + EntityList.getEntityString(entity))
			.replace("%i", "" + EntityList.getEntityID(entity))
			.replace("%x", "" + entity.posX)
			.replace("%y", "" + entity.posY)
			.replace("%z", "" + entity.posZ);
		commandExecutor.addChatMessage(message);
	}

	@Override
	public void printHelpInformation(EntityPlayerSP var1) {
		
	}

	@Override
	public String commandSyntax() {
		return "\u00a7e/summon <entityId|entityName> [x y z]";
	}
}
