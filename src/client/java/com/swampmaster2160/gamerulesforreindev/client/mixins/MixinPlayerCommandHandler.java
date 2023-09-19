package com.swampmaster2160.gamerulesforreindev.client.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.swampmaster2160.gamerulesforreindev.CommandGameRule;

import net.minecraft.client.Minecraft;
import net.minecraft.mitask.PlayerCommandHandler;

@Mixin(PlayerCommandHandler.class)
public abstract class MixinPlayerCommandHandler {
	// Add registering the ramerule command.
	@Inject(method = "registerCommands", at = @At("TAIL"), cancellable = true)
	private void registerCommands(Minecraft mc, CallbackInfo info) {
		((PlayerCommandHandler)(Object)this).addCommand(new CommandGameRule(((PlayerCommandHandler)(Object)this)));
	}
}
