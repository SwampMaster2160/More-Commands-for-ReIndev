package com.swampmaster2160.gamerulesforreindev;

import net.minecraft.src.game.nbt.NBTTagCompound;

public class WorldGameRules {
	private NBTTagCompound rules;

	public WorldGameRules(NBTTagCompound rules) {
		this.rules = rules;
	}

	public WorldGameRules() {
		rules = new NBTTagCompound();
	}

	public NBTTagCompound getNBTTags() {
		return rules;
	}

	public Object getGameRuleValue(String name) {
		// Get the game rule type
		if (!GameRule.doesGameruleExist(name)) throw new RuntimeException("Game rule does not exist: " + name);
		GameRule gameRule = GameRule.getRegisteredGameRule(name);
		// Get game rule datatype
		if (!rules.hasKey(name)) return gameRule.defaultValue;
		Class<?> dataType = gameRule.dataType;
		// Get game rule value
		if (dataType == Boolean.class) return rules.getBoolean(name);
		if (dataType == Byte.class) return rules.getByte(name);
		if (dataType == Short.class) return rules.getShort(name);
		if (dataType == Integer.class) return rules.getInteger(name);
		if (dataType == Long.class) return rules.getLong(name);
		if (dataType == Float.class) return rules.getFloat(name);
		if (dataType == Double.class) return rules.getDouble(name);
		if (dataType == String.class) return rules.getString(name);
		if (dataType == Byte[].class) return rules.getByteArray(name);
		if (dataType == Integer[].class) return rules.getIntArray(name);
		if (dataType == NBTTagCompound.class) return rules.getCompoundTag(name);
		throw new RuntimeException("Unsupported data type: " + dataType);
	}

	public boolean setGameRuleValue(String name, Object value) {
		// Get the game rule type
		if (!GameRule.doesGameruleExist(name)) return false;
		GameRule gameRule = GameRule.getRegisteredGameRule(name);
		// Get game rule datatype
		Class<?> dataType = gameRule.dataType;
		// Set game rule value
		if (value == gameRule.defaultValue) rules.removeTag(name);
		if (dataType == Boolean.class) rules.setBoolean(name, (Boolean)value);
		if (dataType == Byte.class) rules.setByte(name, (Byte)value);
		if (dataType == Short.class) rules.setShort(name, (Short)value);
		if (dataType == Integer.class) rules.setInteger(name, (Integer)value);
		if (dataType == Long.class) rules.setLong(name, (Long)value);
		if (dataType == Float.class) rules.setFloat(name, (Float)value);
		if (dataType == Double.class) rules.setDouble(name, (Double)value);
		if (dataType == String.class) rules.setString(name, (String)value);
		if (dataType == Byte[].class) rules.setByteArray(name, (byte[])value);
		if (dataType == Integer[].class) rules.setIntArray(name, (int[])value);
		if (dataType == NBTTagCompound.class) rules.setCompoundTag(name, (NBTTagCompound)value);
		return true;
	}
}
