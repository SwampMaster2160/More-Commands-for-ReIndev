package com.swampmaster2160.morecommandsforreindev;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import org.jetbrains.annotations.Nullable;

import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.level.World;

public abstract class EntityTargetBinaryOperator {
	private static HashMap<Integer, HashMap<Character, EntityTargetBinaryOperator>> registeredOperators =
		new HashMap<Integer, HashMap<Character, EntityTargetBinaryOperator>>();
	private static HashSet<Character> registeredOperatorSymbols = new HashSet<Character>();
	private static TreeSet<Integer> priorities = new TreeSet<Integer>();

	public static void registerOperator(char symbol, int priority, EntityTargetBinaryOperator operator) {
		@Nullable HashMap<Character, EntityTargetBinaryOperator> operatorsWithSamePriority = registeredOperators.get(priority);
		if (operatorsWithSamePriority == null) {
			operatorsWithSamePriority = new HashMap<Character, EntityTargetBinaryOperator>();
			registeredOperators.put(priority, operatorsWithSamePriority);
			priorities.add(priority);
		}
		registeredOperatorSymbols.add(symbol);
	}

	public static boolean isCharAnOperator(char symbol) {
		return registeredOperatorSymbols.contains(symbol);
	}

	public static Iterator<Integer> getPriorityIterator() {
		return priorities.iterator();
	}

	public static HashMap<Character, EntityTargetBinaryOperator> getOperatorsForPriority(int priority) {
		return registeredOperators.get(priorities);
	}

	public abstract Entity[] getResult(World world, Entity[] a, Entity[] b) {

	}
}
