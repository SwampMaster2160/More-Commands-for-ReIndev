package com.swampmaster2160.morecommandsforreindev.client.mixins;

import java.util.concurrent.atomic.AtomicInteger;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.swampmaster2160.morecommandsforreindev.EntitiesTargeted;
import com.swampmaster2160.morecommandsforreindev.EntityTargets;
import com.swampmaster2160.morecommandsforreindev.MoreCommandsForReIndevClient;

import net.minecraft.mitask.command.CommandErrorHandler;
import net.minecraft.mitask.command.commands.CommandClear;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.entity.player.EntityPlayer;
import net.minecraft.src.game.entity.player.InventoryPlayer;
import net.minecraft.src.game.level.World;
import net.minecraft.src.game.stats.StatCollector;

@Mixin(CommandClear.class)
public class MixinCommandClear {
	@Overwrite
	public void onExecute(String[] args, EntityPlayerSP commandExecutor) {
		// Command can only have up to 4 arguments
		if (args.length > 4) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		// Get world
		World world = commandExecutor.worldObj;
		// Get targets
		Entity[] targets = { commandExecutor };
		if (args.length > 1) {
			@Nullable Entity[] targetsFromSelector = EntityTargets.getTargetsFromSelectorString(world, args[1], commandExecutor.posX, commandExecutor.posY, commandExecutor.posZ, commandExecutor, true, commandExecutor);
			if (targetsFromSelector == null) return;
			targets = targetsFromSelector;
		}
		// Get item id and metadata
		@Nullable Integer itemId = null;
		@Nullable Integer metadata = null;
		if (args.length > 2) {
			itemId = MoreCommandsForReIndevClient.getItemId(args[2]);
			if (itemId == null) {
				String message = StatCollector.translateToLocal("command.invalid_id");
				commandExecutor.addChatMessage(message);
				return;
			}
			metadata = MoreCommandsForReIndevClient.getItemMetadata(itemId, args[2], -1);
			if (metadata == null) {
				String message = StatCollector.translateToLocal("command.invalid_metadata");
				commandExecutor.addChatMessage(message);
				return;
			}
			if (metadata == -1) metadata = null;
		}
		// Get count
		@Nullable Integer count = null;
		if (args.length > 3) {
			try {
				count = Integer.parseInt(args[3]);
			}
			catch (NumberFormatException e) {
				CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
				return;
			}
			if (count < 0) {
				String message = StatCollector.translateToLocal("command.clear.invalid_count");
				commandExecutor.addChatMessage(message);
				return;
			}
		}
		// Clear items
		long itemsRemoved = 0;
		for (Entity entity : targets) {
			EntityPlayer player = (EntityPlayer)entity;
			@Nullable AtomicInteger itemsLeftToClearFromPlayer = count == null ? null : new AtomicInteger(count);
			InventoryPlayer inventory = player.inventory;
			itemsRemoved += MoreCommandsForReIndevClient.removeItemsFromStacks(inventory.mainInventory, itemId, metadata, itemsLeftToClearFromPlayer);
			itemsRemoved += MoreCommandsForReIndevClient.removeItemsFromStacks(inventory.armorInventory, itemId, metadata, itemsLeftToClearFromPlayer);
		}
		// Print the message
		EntitiesTargeted entitiesTargeted = EntitiesTargeted.fromEntityArray(targets);
		String message = StatCollector.translateToLocal("command.clear.clear_" + entitiesTargeted.getTranslationString())
			.replace("%c", "" + entitiesTargeted.count)
			.replace("%t", "" + itemsRemoved);
		if (entitiesTargeted.name != null) message = message.replace("%n", entitiesTargeted.name);
		commandExecutor.addChatMessage(message);
	}

	@Overwrite
	public String commandSyntax() {
		return "\u00a7e/clear [targets] [id][:metadata] [count>]";
	}
}
