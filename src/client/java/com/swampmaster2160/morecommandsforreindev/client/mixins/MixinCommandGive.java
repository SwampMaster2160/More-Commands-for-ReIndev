package com.swampmaster2160.morecommandsforreindev.client.mixins;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.swampmaster2160.morecommandsforreindev.EntitiesTargeted;
import com.swampmaster2160.morecommandsforreindev.EntityTargets;
import com.swampmaster2160.morecommandsforreindev.MoreCommandsForReIndevClient;

import net.minecraft.mitask.command.CommandErrorHandler;
import net.minecraft.mitask.command.commands.CommandGive;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.entity.player.EntityPlayer;
import net.minecraft.src.game.item.Item;
import net.minecraft.src.game.item.ItemStack;
import net.minecraft.src.game.level.World;
import net.minecraft.src.game.stats.StatCollector;

@Mixin(CommandGive.class)
public class MixinCommandGive {
	@Overwrite
	public void onExecute(String[] args, EntityPlayerSP commandExecutor) {
		// Command should have at least 3 arguments.
		if (args.length < 3 || args.length > 4) {
			CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
			return;
		}
		// Get world
		World world = commandExecutor.worldObj;
		// Get targets
		@Nullable Entity[] targets = EntityTargets.getTargetsFromSelectorString(world, args[1], commandExecutor.posX, commandExecutor.posY, commandExecutor.posZ, commandExecutor, true);
		// Print a syntax error if there was a syntax error parsing the targets
		if (targets == null) {
			String message = StatCollector.translateToLocal("command.target_syntax_error");
			commandExecutor.addChatMessage(message);
			return;
		}
		// Get item id and metadata
		@Nullable Integer itemId = MoreCommandsForReIndevClient.getItemId(args[2]);
		if (itemId == null) {
			String message = StatCollector.translateToLocal("command.invalid_id");
			commandExecutor.addChatMessage(message);
			return;
		}
		@Nullable Integer metadata = MoreCommandsForReIndevClient.getItemMetadata(itemId, args[2], 0);
		if (metadata == null) {
			String message = StatCollector.translateToLocal("command.invalid_metadata");
			commandExecutor.addChatMessage(message);
			return;
		}
		// Get count
		int count = 1;
		if (args.length > 3) {
			try {
				count = Integer.parseInt(args[3]);
			}
			catch (NumberFormatException e) {
				CommandErrorHandler.commandUsageMessage(this.commandSyntax(), commandExecutor);
				return;
			}
		}
		if (count < 1) {
			String message = StatCollector.translateToLocal("command.give.invalid_count");
			commandExecutor.addChatMessage(message);
			return;
		}
		// Get item max stack size
		Item itemClass = Item.itemsList[itemId];
		int itemMaxStackSize = itemClass.getItemStackLimit();
		String itemName = itemClass.getItemName().replace("item.", "").replace("tile.", "");
		// Give items to players
		for (Entity entity : targets) {
			int amountOfItemsLeftToDropToPlayer = count;
			EntityPlayer player = (EntityPlayer)entity;
			while (amountOfItemsLeftToDropToPlayer > itemMaxStackSize) {
				player.dropPlayerItem(new ItemStack(itemId, itemMaxStackSize, metadata));
				amountOfItemsLeftToDropToPlayer -= itemMaxStackSize;
			}
			if (amountOfItemsLeftToDropToPlayer > 0) player.dropPlayerItem(new ItemStack(itemId, amountOfItemsLeftToDropToPlayer, metadata));
		}
		// Print message
		EntitiesTargeted entitiesTargeted = EntitiesTargeted.fromEntityArray(targets);
		String message = StatCollector.translateToLocal("command.give.give_" + entitiesTargeted.getTranslationString())
			.replace("%c", "" + entitiesTargeted.count)
			.replace("%i", itemName)
			.replace("%m", "" + metadata)
			.replace("%l", "" + count);
		if (entitiesTargeted.name != null) message = message.replace("%n", entitiesTargeted.name);
		commandExecutor.addChatMessage(message);
	}

	@Overwrite
	public String commandSyntax() {
		return "\u00a7e/give <targets> <id(optional: :<metadata>)> (optional: <count>)";
	}
}
