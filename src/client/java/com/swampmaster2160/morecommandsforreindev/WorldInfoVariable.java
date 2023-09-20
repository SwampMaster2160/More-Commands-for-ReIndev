package com.swampmaster2160.morecommandsforreindev;

import java.util.HashMap;

import org.jetbrains.annotations.Nullable;

import net.minecraft.src.game.level.World;

public abstract class WorldInfoVariable {
	private static HashMap<String, WorldInfoVariable> variableDisplayNames = new HashMap<String, WorldInfoVariable>();
	private static HashMap<String, WorldInfoVariable> variableLowerCaseAndAliasesNames = new HashMap<String, WorldInfoVariable>();

	public static void registerVariable(String displayName, WorldInfoVariable variable, @Nullable String[] aliases) {
		// Set the display name on the variable
		variable.displayName = displayName;
		// Register display name
		variableDisplayNames.put(displayName, variable);
		// Register the lowercase version of the name
		variableLowerCaseAndAliasesNames.put(displayName.toLowerCase(), variable);
		// Register aliases
		if (aliases == null) return;
		for (String alias : aliases) {
			variableLowerCaseAndAliasesNames.put(alias, variable);
		}
	}

	public static @Nullable WorldInfoVariable getVariable(String name) {
		String lowerCaseName = name.toLowerCase().replace("_", "");
		return variableLowerCaseAndAliasesNames.get(lowerCaseName);
	}

	public String displayName;

	public WorldInfoVariable() {
		
	}

	public abstract String getValueAsString(World world);
	public abstract boolean setValueAsString(World world, String value);
}
