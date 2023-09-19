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
		if (dataType == boolean.class) return rules.getBoolean(name);
		if (dataType == byte.class) return rules.getByte(name);
		if (dataType == short.class) return rules.getShort(name);
		if (dataType == int.class) return rules.getInteger(name);
		if (dataType == long.class) return rules.getLong(name);
		if (dataType == float.class) return rules.getFloat(name);
		if (dataType == double.class) return rules.getDouble(name);
		if (dataType == String.class) return rules.getString(name);
		if (dataType == byte[].class) return rules.getByteArray(name);
		if (dataType == int[].class) return rules.getIntArray(name);
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
		if (dataType == Boolean.class) rules.setBoolean(name, (boolean)value);
		if (dataType == Byte.class) rules.setByte(name, (byte)value);
		if (dataType == Short.class) rules.setShort(name, (short)value);
		if (dataType == Integer.class) rules.setInteger(name, (int)value);
		if (dataType == Long.class) rules.setLong(name, (long)value);
		if (dataType == Float.class) rules.setFloat(name, (float)value);
		if (dataType == Double.class) rules.setDouble(name, (double)value);
		if (dataType == String.class) rules.setString(name, (String)value);
		if (dataType == Byte[].class) rules.setByteArray(name, (byte[])value);
		if (dataType == Integer[].class) rules.setIntArray(name, (int[])value);
		if (dataType == NBTTagCompound.class) rules.setCompoundTag(name, (NBTTagCompound)value);
		return true;
	}
}
