package com.swampmaster2160.morecommandsforreindev;

import java.util.HashMap;

import org.jetbrains.annotations.Nullable;

import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.level.World;

public abstract class EntityTargetSelector {
	private static HashMap<String, EntityTargetSelector> registeredTargetSelectors = new HashMap<String, EntityTargetSelector>();

	public static void registerTargetSelector(String name, EntityTargetSelector targetSelector) {
		registeredTargetSelectors.put(name.toLowerCase(), targetSelector);
	}

	public static @Nullable EntityTargetSelector getRegisteredTargetSelector(String name) {
		return registeredTargetSelectors.get(name.toLowerCase().replaceAll("_", ""));
	}

	public abstract Entity[] getSelectedEntities(World world, double x, double y, double z, @Nullable Entity executerEntity);
}
