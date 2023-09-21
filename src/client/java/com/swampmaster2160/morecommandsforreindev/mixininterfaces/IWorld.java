package com.swampmaster2160.morecommandsforreindev.mixininterfaces;

import net.minecraft.src.game.level.chunk.IChunkProvider;

public abstract interface IWorld {
	IChunkProvider chunkProvider = null;

	public void reCalculateChunkProvider();
}
