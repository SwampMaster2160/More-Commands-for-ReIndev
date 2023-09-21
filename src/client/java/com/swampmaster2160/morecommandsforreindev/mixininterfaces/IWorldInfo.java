package com.swampmaster2160.morecommandsforreindev.mixininterfaces;

public abstract interface IWorldInfo {
	public long randomSeed = 0;
	public boolean mapFeaturesEnabled = false;
	public int dimension = 0;

	public abstract void setRandomSeed(long seed);
	public abstract void setMapFeaturesEnabled(boolean enabled);
	public abstract void setDimension(int dimension);
}
