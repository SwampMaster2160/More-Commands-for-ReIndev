package com.swampmaster2160.morecommandsforreindev.mixininterfaces;

public abstract interface IWorldInfo {
	public long randomSeed = 0;
	public boolean mapFeaturesEnabled = false;
	public int dimension = 0;
	public boolean hardcore = false;
	public long lastTimePlayed = 0;
	public short highestChunkOW = 128;
	public short highestChunkNether = 16;
	public short lowestChunkOW = 0;
	public short lowestChunkNether = 0;

	public abstract void setRandomSeed(long seed);
	public abstract void setMapFeaturesEnabled(boolean enabled);
	public abstract void setDimension(int dimension);
	public void setHardcoreEnabled(boolean enabled);
	public void setLastTimePlayed(long value);
	public void setHighestChunkOW(short value);
	public void setHighestChunkNether(short value);
	public void setLowestChunkOW(short value);
	public void setLowestChunkNether(short value);
}
