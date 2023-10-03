package com.swampmaster2160.morecommandsforreindev.entitytargetselectors;

import org.jetbrains.annotations.Nullable;

import com.swampmaster2160.morecommandsforreindev.EntityTargetSelector;

import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.level.World;

public class EntityTargetSelectorExecutingEntity extends EntityTargetSelector {
	@Override
	public Entity[] getSelectedEntities(World world, double x, double y, double z, @Nullable Entity executerEntity) {
		if (executerEntity == null) return new Entity[] {};
		return new Entity[] { executerEntity };
	}
}
