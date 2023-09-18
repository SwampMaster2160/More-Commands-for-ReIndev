package com.swampmaster2160.gamerulesforreindev.client.mixins;

import net.minecraft.src.game.level.WorldInfo;
import net.minecraft.src.game.nbt.NBTTagCompound;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.swampmaster2160.gamerulesforreindev.GameRules;

import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldInfo.class)
public class MixinWorldInfo {
	private GameRules gameRules;

	public GameRules getGameRules() {
		return gameRules;
	}

	// When loading a world, also load the gamerules if they exist.
	@Inject(method = "<init>(Lnet/minecraft/src/game/nbt/NBTTagCompound;)V", at = @At("TAIL"), cancellable = true)
	public void WorldInfo(NBTTagCompound nBTTagCompound, CallbackInfo info) {
		System.out.println("A");
		// Load the world's gamerules it has them.
		if (nBTTagCompound.hasKey("GameRules")) {
			this.gameRules = new GameRules(nBTTagCompound.getCompoundTag("GameRules"));
		}
		// Else create a deafult gamerule set.
		else {
			this.gameRules = new GameRules();
		}
	}

	// When changing dimensions
	@Inject(method = "<init>(Lnet/minecraft/src/game/level/WorldInfo;)V", at = @At("TAIL"), cancellable = true)
	public void WorldInfo(WorldInfo worldInfo, CallbackInfo info) {
		System.out.println("B");
		this.gameRules = ((MixinWorldInfo)(Object)worldInfo).gameRules;
	}

	// When saving a world, save the game rules as a "GameRules" nbt compound tag
	@Inject(method = "updateTagCompound", at = @At("TAIL"), cancellable = true)
	private void updateTagCompound(NBTTagCompound nBTTagCompound, NBTTagCompound arg2, CallbackInfo info) {
		System.out.println("C");
		nBTTagCompound.setCompoundTag("GameRules", this.gameRules.getNBTTags());
	}
}
