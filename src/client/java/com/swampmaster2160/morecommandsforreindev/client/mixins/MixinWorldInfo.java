package com.swampmaster2160.morecommandsforreindev.client.mixins;

import org.spongepowered.asm.mixin.Mixin;

import com.swampmaster2160.morecommandsforreindev.mixininterfaces.IWorldInfo;

import net.minecraft.src.game.level.WorldInfo;

@Mixin(WorldInfo.class)
public abstract class MixinWorldInfo implements IWorldInfo {
	public long randomSeed;
	public boolean mapFeaturesEnabled;
	public int dimension;
	public boolean hardcore;

	@Override
	public void setRandomSeed(long seed) {
		randomSeed = seed;
	}

	@Override
	public void setMapFeaturesEnabled(boolean enabled) {
		mapFeaturesEnabled = enabled;
	}

	@Override
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	@Override
	public void setHardcoreEnabled(boolean enabled) {
		this.hardcore = enabled;
	}
}
