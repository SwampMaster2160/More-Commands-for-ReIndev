package com.swampmaster2160.morecommandsforreindev.client.mixins;

import org.spongepowered.asm.mixin.Mixin;

import com.swampmaster2160.morecommandsforreindev.IWorldInfo;

import net.minecraft.src.game.level.WorldInfo;

@Mixin(WorldInfo.class)
public abstract class MixinWorldInfo implements IWorldInfo {
	public long randomSeed;

	@Override
	public void setRandomSeed(long seed) {
		randomSeed = seed;
	}
}