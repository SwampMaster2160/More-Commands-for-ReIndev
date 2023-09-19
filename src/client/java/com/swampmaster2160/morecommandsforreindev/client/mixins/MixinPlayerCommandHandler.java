package com.swampmaster2160.morecommandsforreindev.client.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.swampmaster2160.morecommandsforreindev.commands.CommandWorldInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.mitask.PlayerCommandHandler;

@Mixin(PlayerCommandHandler.class)
public abstract class MixinPlayerCommandHandler {
	// Add registering the ramerule command.
	@Inject(method = "registerCommands", at = @At("TAIL"), cancellable = true)
	private void registerCommands(Minecraft mc, CallbackInfo info) {
		((PlayerCommandHandler)(Object)this).addCommand(new CommandWorldInfo(((PlayerCommandHandler)(Object)this)));
	}
}
