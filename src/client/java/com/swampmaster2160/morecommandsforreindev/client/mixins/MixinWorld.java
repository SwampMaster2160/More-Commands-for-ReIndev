package com.swampmaster2160.morecommandsforreindev.client.mixins;

import org.spongepowered.asm.mixin.Mixin;

import com.swampmaster2160.morecommandsforreindev.IWorld;

import net.minecraft.src.game.level.World;
import net.minecraft.src.game.level.chunk.ChunkProvider;
import net.minecraft.src.game.level.chunk.IChunkLoader;
import net.minecraft.src.game.level.chunk.IChunkProvider;

@Mixin(World.class)
public abstract class MixinWorld implements IWorld {
	IChunkProvider chunkProvider;

	public void reCalculateChunkProvider() {
		((World)(Object)this).saveWorld(true, null);
		IChunkLoader chunkloader = ((World)(Object)this).func_40479_y().getChunkLoader(((World)(Object)this).worldProvider);
        this.chunkProvider = new ChunkProvider(((World)(Object)this), chunkloader, ((World)(Object)this).worldProvider.getChunkProvider());
	}
}
