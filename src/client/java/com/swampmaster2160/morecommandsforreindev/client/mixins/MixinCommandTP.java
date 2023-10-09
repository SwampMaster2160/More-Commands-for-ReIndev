package com.swampmaster2160.morecommandsforreindev.client.mixins;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.swampmaster2160.morecommandsforreindev.EntityTargets;
import com.swampmaster2160.morecommandsforreindev.MoreCommandsForReIndev;

import net.minecraft.mitask.command.CommandErrorHandler;
import net.minecraft.mitask.command.commands.CommandTP;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.level.World;
import net.minecraft.src.game.stats.StatCollector;

@Mixin(CommandTP.class)
public class MixinCommandTP {
	@Inject(method = "onExecute", at = @At("HEAD"), cancellable = true)
	private void onExecute(String[] args, EntityPlayerSP commandExecutor, CallbackInfo info) {
		if (args.length == 5) {
			onExecute5ArgCommand(args, commandExecutor);
			info.cancel();
		}
	}

	private void onExecute5ArgCommand(String[] args, EntityPlayerSP commandExecutor) {
		// Unpack arguments
		String targetsArg = args[1];
		String xArg = args[2];
		String yArg = args[3];
		String zArg = args[4];
		// Get world object
		World world = commandExecutor.worldObj;
		// Get targets
		@Nullable Entity[] targets = EntityTargets.getTargetsFromSelectorString(world, targetsArg, commandExecutor.posX, commandExecutor.posY, commandExecutor.posZ, commandExecutor);
		if (targets == null) {
			String message = StatCollector.translateToLocal("command.tp.target_syntax_error");
			commandExecutor.addChatMessage(message);
			return;
		}
		// Get pos to tp to
		@Nullable Double x = MoreCommandsForReIndev.parseDoubleCoordinate(xArg, commandExecutor.posX);
		@Nullable Double y = MoreCommandsForReIndev.parseDoubleCoordinate(yArg, commandExecutor.posY);
		@Nullable Double z = MoreCommandsForReIndev.parseDoubleCoordinate(zArg, commandExecutor.posZ);
		if (x == null || y == null || z == null) {
			CommandErrorHandler.commandUsageMessage(((CommandTP)(Object)this).commandSyntax(), commandExecutor);
			return;
		}
		// Teleport entities
		for (Entity targetEntity: targets) {
			targetEntity.setPosition(x, y, z);
		}
		// Print message
		// Print kill message
		if (targets.length == 0) {
			String message = StatCollector.translateToLocal("command.tp.tp_none");
			commandExecutor.addChatMessage(message);
			return;
		}
		if (targets.length == 1) {
			String message = StatCollector.translateToLocal("command.tp.tp_one");
			commandExecutor.addChatMessage(message);
			return;
		}
		String message = StatCollector.translateToLocal("command.tp.tp_multi")
			.replace("%c", "" + targets.length);
		commandExecutor.addChatMessage(message);
	}
}
