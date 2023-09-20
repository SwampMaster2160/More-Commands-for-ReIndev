package com.swampmaster2160.morecommandsforreindev;

public abstract interface IWorldInfo {
	public long randomSeed = 0;
	public boolean mapFeaturesEnabled = false;

	public abstract void setRandomSeed(long seed);
	public abstract void setMapFeaturesEnabled(boolean enabled);
}
