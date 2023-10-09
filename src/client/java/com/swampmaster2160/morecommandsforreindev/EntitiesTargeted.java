package com.swampmaster2160.morecommandsforreindev;

import org.jetbrains.annotations.Nullable;

import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.entity.EntityList;

public enum EntitiesTargeted {
	NO_ENTITIES,
	MULTIPLE_ENTITIES,
	MULTIPLE_PLAYERS,
	MULTIPLE_ENTITIES_OF_THE_SAME_TYPE,
	ONE_ENTITY,
	ONE_PLAYER,
	ONE_ENTITY_OF_A_TYPE;

	public int count = 0;
	public @Nullable String name = null;

	public static EntitiesTargeted fromEntityArray(Entity[] entities) {
		if (entities.length == 0) return NO_ENTITIES;
		if (entities.length == 1) {
			Entity entity = entities[0];
			if (entity instanceof EntityPlayerSP) {
				EntitiesTargeted out = ONE_PLAYER;
				out.name = ((EntityPlayerSP)entity).username;
				return out;
			}
			@Nullable String entityName = EntityList.getEntityString(entity);
			if (entityName == null) return ONE_ENTITY;
			EntitiesTargeted out = ONE_ENTITY_OF_A_TYPE;
			out.name = entityName;
			return out;
		}
		@Nullable String entitiesName = EntityList.getEntityString(entities[0]);
		boolean isAllPlayers = true;
		boolean isAllSameEntityType = true;
		for (Entity entity : entities) {
			if (!(entity instanceof EntityPlayerSP)) isAllPlayers = false;
			@Nullable String entityName = EntityList.getEntityString(entity);
			if (entitiesName == null || entityName == null || !entityName.equals(entitiesName)) isAllSameEntityType = false;
		}
		if (isAllPlayers) {
			EntitiesTargeted out = MULTIPLE_PLAYERS;
			out.count = entities.length;
			return out;
		}
		if (isAllSameEntityType) {
			EntitiesTargeted out = MULTIPLE_ENTITIES_OF_THE_SAME_TYPE;
			out.count = entities.length;
			out.name = entitiesName;
			return out;
		}
		EntitiesTargeted out = MULTIPLE_ENTITIES;
		out.count = entities.length;
		return out;
	}

	public String getTranslationString() {
		switch (this) {
			case NO_ENTITIES:
				return "none";
			case ONE_ENTITY:
				return "one";
			case ONE_ENTITY_OF_A_TYPE:
				return "one_of_type";
			case ONE_PLAYER:
				return "one_player";
			case MULTIPLE_ENTITIES:
				return "multi";
			case MULTIPLE_ENTITIES_OF_THE_SAME_TYPE:
				return "multi_of_type";
			case MULTIPLE_PLAYERS:
				return "multi_player";
		}
		throw new RuntimeException("Translation string does not exist for value");
	}
}
