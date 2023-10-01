package com.swampmaster2160.morecommandsforreindev;

import org.jetbrains.annotations.Nullable;

import com.fox2code.foxloader.loader.Mod;

public class MoreCommandsForReIndev extends Mod {
	@Override
	public void onPreInit() {
		
	}

	public static @Nullable Boolean parseBool(String string) {
		switch (string.toLowerCase().replace("_", "")) {
			case "true":
			case "t":
			case "1":
			case "yes":
			case "on":
				return true;
			case "false":
			case "f":
			case "0":
			case "no":
			case "off":
				return false;
			default:
				return null;
		}
	}

	public static @Nullable Integer parseIntCoordinate(String argument, int commandExecutorCoordinate) {
		if (argument.isEmpty()) return null;
		int out = 0;
		if (argument.startsWith("~")) {
			argument = argument.substring(1);
			if (argument.isEmpty()) return commandExecutorCoordinate;
			out = commandExecutorCoordinate;
		}
		try {
			return out + Integer.parseInt(argument);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

	public static @Nullable Double parseDoubleCoordinate(String argument, double commandExecutorCoordinate) {
		if (argument.isEmpty()) return null;
		double out = 0;
		if (argument.startsWith("~")) {
			argument = argument.substring(1);
			if (argument.isEmpty()) return commandExecutorCoordinate;
			out = commandExecutorCoordinate;
		}
		try {
			return out + Double.parseDouble(argument);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
}
