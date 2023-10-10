package com.swampmaster2160.morecommandsforreindev.client.mixins;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.swampmaster2160.morecommandsforreindev.EntityTargets;
import com.swampmaster2160.morecommandsforreindev.MoreCommandsForReIndevClient;

import net.minecraft.mitask.command.CommandErrorHandler;
import net.minecraft.mitask.command.commands.CommandGive;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.entity.Entity;
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
			String message = StatCollector.translateToLocal("command.give.target_syntax_error");
			commandExecutor.addChatMessage(message);
			return;
		}
		// Get item id and metadata
		@Nullable Integer itemId = MoreCommandsForReIndevClient.getItemId(args[2]);
		@Nullable Integer metadata = MoreCommandsForReIndevClient.getItemMetadata(args[2]);
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
	}

	@Overwrite
	public String commandSyntax() {
		return "\u00a7e/give <targets> <id(optional: :<metadata>)> (optional: <count>)";
	}
}
