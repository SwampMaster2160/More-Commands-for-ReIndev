package com.swampmaster2160.gamerulesforreindev.client.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.src.game.level.WorldClient;

@Mixin(WorldClient.class)
public class MixinWorldClient {
	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	public void tick() {
		((WorldClient)(Object)this).setWorldTime(((WorldClient)(Object)this).getWorldTime() - 100L);
	}
}
