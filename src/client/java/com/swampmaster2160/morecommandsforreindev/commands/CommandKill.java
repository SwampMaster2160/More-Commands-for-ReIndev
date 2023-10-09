package com.swampmaster2160.morecommandsforreindev.commands;

import org.jetbrains.annotations.Nullable;

import com.swampmaster2160.morecommandsforreindev.EntityTargets;

import net.minecraft.mitask.PlayerCommandHandler;
import net.minecraft.mitask.command.Command;
import net.minecraft.mitask.command.CommandErrorHandler;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.entity.EntityList;
import net.minecraft.src.game.entity.EntityLiving;
import net.minecraft.src.game.level.World;
import net.minecraft.src.game.stats.StatCollector;

public class CommandKill extends Command {
	public CommandKill(PlayerCommandHandler commandHandler) {
		super("kill", true, false, (String[])null);
	}

	@Override
	public void onExecute(String[] args, EntityPlayerSP commandExecutor) {
		World world = commandExecutor.worldObj;
		// Kill executer if /kill does not have any extra arguments
		if (args.length == 1) {
			Entity[] entities = { commandExecutor };
			killEntities(commandExecutor, entities);
			return;
		}
		// /kill cannot have more than 2 arguments
		if (args.length > 2) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		// Get targets
		@Nullable Entity[] targets = EntityTargets.getTargetsFromSelectorString(world, args[1], commandExecutor.posX, commandExecutor.posY, commandExecutor.posZ, commandExecutor);
		// Print a syntax error if there was a syntax error parsing the targets
		if (targets == null) {
			String message = StatCollector.translateToLocal("command.kill.target_syntax_error");
			commandExecutor.addChatMessage(message);
			return;
		}
		// Kill targeted entities
		killEntities(commandExecutor, targets);
	}

	/**
	 * Kills all specified entities
	 * @param commandExecutor Info about the executer
	 * @param entitiesToKill An array of all entities that should be killed
	 */
	void killEntities(EntityPlayerSP commandExecutor, Entity[] entitiesToKill) {
		// Get world
		World world = commandExecutor.worldObj;
		// Kill all entities
		for (Entity entity: entitiesToKill) {
			// Kill the entity properly if we can
			entity.attackEntityFrom(null, Integer.MAX_VALUE);
			// Otherwise try to kill the entity via a negative heal trick
			if (entity.isEntityAlive() && entity instanceof EntityLiving) ((EntityLiving)entity).heal(Integer.MIN_VALUE);
			// Othersise just delete the entity
			if (entity.isEntityAlive()) world.getLoadedEntityList().remove(entity);
		}
		// Print kill message
		if (entitiesToKill.length == 0) {
			String message = StatCollector.translateToLocal("command.kill.kill_none");
			commandExecutor.addChatMessage(message);
			return;
		}
		if (entitiesToKill.length != 1) {
			@Nullable String entityName = EntityList.getEntityString(entitiesToKill[0]);
			boolean isAllSameName = true;
			boolean isAllPlayers = true;
			for (Entity entity: entitiesToKill) {
				if (entityName != null) {
					@Nullable String otherEntityName = EntityList.getEntityString(entity);
					if (otherEntityName == null || !otherEntityName.equals(entityName)) isAllSameName = false;
					if (!(entity instanceof EntityPlayerSP)) isAllPlayers = false;
				}
				
			}
			if (isAllPlayers) {
				String message = StatCollector.translateToLocal("command.kill.kill_multi_player")
					.replace("%c", "" + entitiesToKill.length);
				commandExecutor.addChatMessage(message);
				return;
			}
			if (isAllSameName) {
				String message = StatCollector.translateToLocal("command.kill.kill_multi_entity")
					.replace("%c", "" + entitiesToKill.length)
					.replace("%n", entityName);
				commandExecutor.addChatMessage(message);
				return;
			}
			String message = StatCollector.translateToLocal("command.kill.kill_multi")
				.replace("%c", "" + entitiesToKill.length);
			commandExecutor.addChatMessage(message);
			return;
		}
		// Kill message for when only one entity is killed
		Entity entity = entitiesToKill[0];
		if (entity instanceof EntityPlayerSP) {
			EntityPlayerSP player = (EntityPlayerSP)entity;
			String message = StatCollector.translateToLocal("command.kill.kill_one_player")
				.replace("%n", "" + player.username);
			commandExecutor.addChatMessage(message);
			return;
		}
		@Nullable String entityName = EntityList.getEntityString(entity);
		if (entityName != null) {
			String message = StatCollector.translateToLocal("command.kill.kill_one_entity")
				.replace("%n", entityName);
			commandExecutor.addChatMessage(message);
			return;
		}
		String message = StatCollector.translateToLocal("command.kill.kill_one");
		commandExecutor.addChatMessage(message);
		return;
	}

	@Override
	public void printHelpInformation(EntityPlayerSP var1) {
		// Prints usage and help by default
	}

	@Override
	public String commandSyntax() {
		return "\u00a7e/kill <targets>";
	}
}
