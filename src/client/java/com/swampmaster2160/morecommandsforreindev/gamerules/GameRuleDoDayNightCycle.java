package com.swampmaster2160.morecommandsforreindev.gamerules;

import org.jetbrains.annotations.Nullable;

import com.swampmaster2160.morecommandsforreindev.GameRule;

public class GameRuleDoDayNightCycle extends GameRule {
	public GameRuleDoDayNightCycle() {
		super(boolean.class, true);
	}

	@Override
	public @Nullable Object parseCommandString(String stringFromCommand) {
		return parseBoolean(stringFromCommand);
	}
}
