package com.swampmaster2160.gamerulesforreindev.gamerules;

import org.jetbrains.annotations.Nullable;

import com.swampmaster2160.gamerulesforreindev.GameRule;

public class GameRuleKeepInventory extends GameRule {
	public GameRuleKeepInventory() {
		super(boolean.class, false);
	}

	@Override
	public @Nullable Object parseCommandString(String stringFromCommand) {
		return parseBoolean(stringFromCommand);
	}
}
