package com.swampmaster2160.gamerulesforreindev;

import java.util.HashMap;

import org.jetbrains.annotations.Nullable;

public abstract class GameRule {
	static HashMap<String, GameRule> registeredGameRules = new HashMap<String, GameRule>();
	public Class<?> dataType;
	public Object defaultValue;

	public static void registerGameRule(String name, GameRule gameruleToRegister) {
		registeredGameRules.put(name, gameruleToRegister);
	}

	public static boolean doesGameruleExist(String name) {
		return registeredGameRules.containsKey(name);
	}

	public static GameRule getRegisteredGameRule(String name) {
		return registeredGameRules.get(name);
	}

	public GameRule(Class<?> dataType, Object defaultValue) {
		this.dataType = dataType;
		this.defaultValue = defaultValue;
	}

	public abstract @Nullable Object parseCommandString(String stringFromCommand);

	protected @Nullable Boolean parseBoolean(String stringFromCommand) {
		switch (stringFromCommand.toLowerCase()) {
			case "true":
			case "yes":
			case "on":
			case "1":
			case "t":
				return true;
			case "false":
			case "no":
			case "off":
			case "0":
			case "f":
				return false;
			default:
				return null;
		}
	}
}
