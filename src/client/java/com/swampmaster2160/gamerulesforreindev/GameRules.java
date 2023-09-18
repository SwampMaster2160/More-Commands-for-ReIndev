package com.swampmaster2160.gamerulesforreindev;

import net.minecraft.src.game.nbt.NBTTagCompound;

public class GameRules {
	NBTTagCompound rules;

	public GameRules(NBTTagCompound rules) {
		this.rules = rules;
	}

	public GameRules() {
		rules = new NBTTagCompound();
	}

	public NBTTagCompound getNBTTags() {
		return rules;
	}
}
