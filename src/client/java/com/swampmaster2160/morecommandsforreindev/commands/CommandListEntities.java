package com.swampmaster2160.morecommandsforreindev.commands;

import org.jetbrains.annotations.Nullable;

import com.swampmaster2160.morecommandsforreindev.EntityTargets;

import net.minecraft.mitask.PlayerCommandHandler;
import net.minecraft.mitask.command.Command;
import net.minecraft.mitask.command.CommandErrorHandler;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.entity.EntityList;
import net.minecraft.src.game.entity.player.EntityPlayer;
import net.minecraft.src.game.level.World;
import net.minecraft.src.game.stats.StatCollector;

public class CommandListEntities extends Command {
	public CommandListEntities(PlayerCommandHandler commandHandler) {
		super("listentities", true, false, new String[] { "le" });
	}

	@Override
	public void onExecute(String[] args, EntityPlayerSP commandExecutor) {
		World world = commandExecutor.worldObj;
		// /listentities cannot have more than 2 arguments
		if (args.length > 2) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		// Get targets
		String targetsString = "@e";
		if (args.length == 2) targetsString = args[1];
		@Nullable Entity[] targets = EntityTargets.getTargetsFromSelectorString(world, targetsString, commandExecutor.posX, commandExecutor.posY, commandExecutor.posZ, commandExecutor, false, commandExecutor);
		// Print a syntax error if there was a syntax error parsing the targets
		if (targets == null) return;
		// List targeted entities
		for (Entity targetEntity: targets) {
			if (targetEntity instanceof EntityPlayer) {
				String message = StatCollector.translateToLocal("command.listentities.entity_info_player")
					.replace("%n", "" + ((EntityPlayer)targetEntity).username)
					.replace("%a", "" + targetEntity.entityId)
					.replace("%x", "" + targetEntity.posX)
					.replace("%y", "" + targetEntity.posY)
					.replace("%z", "" + targetEntity.posZ);
				commandExecutor.addChatMessage(message);
				return;
			}
			@Nullable String entityName =  EntityList.getEntityString(targetEntity);
			if (entityName == null) {
				String message = StatCollector.translateToLocal("command.listentities.entity_info_no_name")
					.replace("%a", "" + targetEntity.entityId)
					.replace("%x", "" + targetEntity.posX)
					.replace("%y", "" + targetEntity.posY)
					.replace("%z", "" + targetEntity.posZ);
				commandExecutor.addChatMessage(message);
				return;
			}
			String message = StatCollector.translateToLocal("command.listentities.entity_info")
				.replace("%n", "" + EntityList.getEntityString(targetEntity))
				.replace("%i", "" + EntityList.getEntityID(targetEntity))
				.replace("%a", "" + targetEntity.entityId)
				.replace("%x", "" + targetEntity.posX)
				.replace("%y", "" + targetEntity.posY)
				.replace("%z", "" + targetEntity.posZ);
			commandExecutor.addChatMessage(message);
		}
	}

	@Override
	public void printHelpInformation(EntityPlayerSP var1) {
		// Prints usage and help by default
	}

	@Override
	public String commandSyntax() {
		return "\u00a7e/listentities <targets>";
	}
}
