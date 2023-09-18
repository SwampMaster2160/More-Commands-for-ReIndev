package com.swampmaster2160.gamerulesforreindev;

import java.util.HashMap;

public abstract class GameRule {
	static HashMap<String, GameRule> registeredGameRules = new HashMap<String, GameRule>();
	public Class<?> dataType;
	public Object defaultValue;

	public static void registerGameRule(String name, GameRule gameruleToRegister) {
		registeredGameRules.put(name, gameruleToRegister);
	}

	public static Boolean doesGameruleExist(String name) {
		return registeredGameRules.containsKey(name);
	}

	public static GameRule getRegisteredGameRule(String name) {
		return registeredGameRules.get(name);
	}

	public GameRule(Class<?> dataType, Object defaultValue) {
		this.dataType = dataType;
		this.defaultValue = defaultValue;
	}
}
