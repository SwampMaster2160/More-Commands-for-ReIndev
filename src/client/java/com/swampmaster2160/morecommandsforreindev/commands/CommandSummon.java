package com.swampmaster2160.morecommandsforreindev.commands;

import org.jetbrains.annotations.Nullable;

import net.minecraft.mitask.PlayerCommandHandler;
import net.minecraft.mitask.command.Command;
import net.minecraft.mitask.command.CommandErrorHandler;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.entity.EntityList;
import net.minecraft.src.game.level.World;
import net.minecraft.src.game.stats.StatCollector;

public class CommandSummon extends Command {
	public CommandSummon(PlayerCommandHandler commandHandler) {
		super("summon", true, false, (String[])null);
	}

	@Override
	public void onExecute(String[] args, EntityPlayerSP commandExecutor) {
		if (args.length != 5 && args.length != 2) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		String entityNameOrId = args[1];
		String xArg = "~";
		String yArg = "~";
		String zArg = "~";
		if (args.length == 5) {
			xArg = args[2];
			yArg = args[3];
			zArg = args[4];
		}
		if (xArg.isEmpty() || yArg.isEmpty() || zArg.isEmpty()) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		float x = 0;
		float y = 0;
		float z = 0;
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
			x += Float.parseFloat(xArg);
			y += Float.parseFloat(yArg);
			z += Float.parseFloat(zArg);
		}
		catch (NumberFormatException e) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		World world = commandExecutor.worldObj;
		@Nullable Entity entity = null;
		try {
			int entityId = Integer.parseInt(entityNameOrId);
			if (entityId == 1 || entityId == 3 || entityId == 9 || entityId == 13) {
				entityId = -1;
			}
			entity = EntityList.createEntity(entityId, world);
		}
		catch (NumberFormatException e) {
			if (entityNameOrId.equals("Item") || entityNameOrId.equals("FireResistantItem") || entityNameOrId.equals("Painting") || entityNameOrId.equals("SplashPotion")) {
				entityNameOrId = "";
			}
			entity = EntityList.createEntityInWorld(entityNameOrId, world);
		}
		if (entity == null) {
			String message = StatCollector.translateToLocal("command.summon.unable_to_summon");
			commandExecutor.addChatMessage(message);
			return;
		}
		entity.setPosition(x, y, z);
		world.entityJoinedWorld(entity);
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
