package com.swampmaster2160.morecommandsforreindev.entitytargetselectors;

import org.jetbrains.annotations.Nullable;

import com.swampmaster2160.morecommandsforreindev.EntityTargetSelector;

import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.level.World;

public class EntityTargetSelectorRandomPlayer extends EntityTargetSelector {
	@Override
	public Entity[] getSelectedEntities(World world, double x, double y, double z, @Nullable Entity executerEntity) {
		Entity[] players = world.getRegisteredNetworkPlayers().toArray(new Entity[] {});
		if (players.length == 0) return new Entity[] {};
		int index = world.rand.nextInt(players.length);
		return new Entity[] { players[index] };
	}
}
