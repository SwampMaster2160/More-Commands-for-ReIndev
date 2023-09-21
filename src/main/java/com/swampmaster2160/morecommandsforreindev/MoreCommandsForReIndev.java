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
}
