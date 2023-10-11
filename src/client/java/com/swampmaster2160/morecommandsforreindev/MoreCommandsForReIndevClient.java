package com.swampmaster2160.morecommandsforreindev;

import java.util.concurrent.atomic.AtomicInteger;

import org.jetbrains.annotations.Nullable;

import com.fox2code.foxloader.loader.ClientMod;
import com.swampmaster2160.morecommandsforreindev.commands.CommandSummon;
import com.swampmaster2160.morecommandsforreindev.entitytargetbinaryoperator.EntityTargetBinaryOperatorAnd;
import com.swampmaster2160.morecommandsforreindev.entitytargetbinaryoperator.EntityTargetBinaryOperatorOr;
import com.swampmaster2160.morecommandsforreindev.entitytargetbinaryoperator.EntityTargetBinaryOperatorXor;
import com.swampmaster2160.morecommandsforreindev.entitytargetselectors.EntityTargetSelectorAllEntities;
import com.swampmaster2160.morecommandsforreindev.entitytargetselectors.EntityTargetSelectorAllPlayers;
import com.swampmaster2160.morecommandsforreindev.entitytargetselectors.EntityTargetSelectorExecutingEntity;
import com.swampmaster2160.morecommandsforreindev.entitytargetselectors.EntityTargetSelectorNearestPlayer;
import com.swampmaster2160.morecommandsforreindev.entitytargetselectors.EntityTargetSelectorRandomPlayer;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableCheats;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableDimension;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableFeatures;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableGameType;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableGenerator;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableHardcore;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableHighestChunkNether;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableHighestChunkOverworld;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableHighscore;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableLastTimePlayed;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableLowestChunkNether;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableLowestChunkOverworld;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableRainTime;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableRaining;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableSaveVersion;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableSeed;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableSizeOnDisk;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableSpawnPos;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableSpawnX;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableSpawnY;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableSpawnZ;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableThunderTime;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableThundering;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableWorldName;
import com.swampmaster2160.morecommandsforreindev.worldinfovariable.WorldInfoVariableWorldTime;

import net.minecraft.src.game.item.Item;
import net.minecraft.src.game.item.ItemBlock;
import net.minecraft.src.game.item.ItemStack;

public class MoreCommandsForReIndevClient extends MoreCommandsForReIndev implements ClientMod {
	@Override
	public void onInit() {
		// Client specific code

		// Register world info variables
		WorldInfoVariable.registerVariable("seed", new WorldInfoVariableSeed(), new String[] {"s"});
		WorldInfoVariable.registerVariable("generator", new WorldInfoVariableGenerator(), new String[] {"g"});
		WorldInfoVariable.registerVariable("features", new WorldInfoVariableFeatures(), new String[] {"f"});
		WorldInfoVariable.registerVariable("spawnX", new WorldInfoVariableSpawnX(), new String[] {"sx"});
		WorldInfoVariable.registerVariable("spawnY", new WorldInfoVariableSpawnY(), new String[] {"sy"});
		WorldInfoVariable.registerVariable("spawnZ", new WorldInfoVariableSpawnZ(), new String[] {"sz"});
		WorldInfoVariable.registerVariable("worldTime", new WorldInfoVariableWorldTime(), new String[] {"wt"});
		WorldInfoVariable.registerVariable("sizeOnDisk", new WorldInfoVariableSizeOnDisk(), new String[] {"sd"});
		WorldInfoVariable.registerVariable("dimension", new WorldInfoVariableDimension(), new String[] {"d"});
		WorldInfoVariable.registerVariable("raining", new WorldInfoVariableRaining(), new String[] {"r"});
		WorldInfoVariable.registerVariable("thundering", new WorldInfoVariableThundering(), new String[] {"t"});
		WorldInfoVariable.registerVariable("rainTime", new WorldInfoVariableRainTime(), new String[] {"rt"});
		WorldInfoVariable.registerVariable("thunderTime", new WorldInfoVariableThunderTime(), new String[] {"tt"});
		WorldInfoVariable.registerVariable("gameType", new WorldInfoVariableGameType(), new String[] {"gt"});
		WorldInfoVariable.registerVariable("saveVersion", new WorldInfoVariableSaveVersion(), new String[] {"sv"});
		WorldInfoVariable.registerVariable("cheats", new WorldInfoVariableCheats(), new String[] {"c"});
		WorldInfoVariable.registerVariable("hardcore", new WorldInfoVariableHardcore(), new String[] {"h"});
		WorldInfoVariable.registerVariable("lastTimePlayed", new WorldInfoVariableLastTimePlayed(), new String[] {"ltp"});
		WorldInfoVariable.registerVariable("highscore", new WorldInfoVariableHighscore(), new String[] {"hs"});
		WorldInfoVariable.registerVariable("highestChunkOverworld", new WorldInfoVariableHighestChunkOverworld(), new String[] {"hco"});
		WorldInfoVariable.registerVariable("highestChunkNether", new WorldInfoVariableHighestChunkNether(), new String[] {"hcn"});
		WorldInfoVariable.registerVariable("lowestChunkOverworld", new WorldInfoVariableLowestChunkOverworld(), new String[] {"lco"});
		WorldInfoVariable.registerVariable("lowestChunkNether", new WorldInfoVariableLowestChunkNether(), new String[] {"lcn"});
		WorldInfoVariable.registerVariable("worldName", new WorldInfoVariableWorldName(), new String[] {"wn"});
		WorldInfoVariable.registerVariable("spawnPos", new WorldInfoVariableSpawnPos(), new String[] {"sp"});
		// A list of items that cannot be summoned without crashing the game unless extra data is supplied to the entity
		CommandSummon.addBadEnitity(1, "Item");
		CommandSummon.addBadEnitity(3, "FireResistantItem");
		CommandSummon.addBadEnitity(9, "Painting");
		CommandSummon.addBadEnitity(13, "SplashPotion");
		// Register entity target selectors
		EntityTargetSelector.registerTargetSelector("e", new EntityTargetSelectorAllEntities());
		EntityTargetSelector.registerTargetSelector("a", new EntityTargetSelectorAllPlayers());
		EntityTargetSelector.registerTargetSelector("s", new EntityTargetSelectorExecutingEntity());
		EntityTargetSelector.registerTargetSelector("p", new EntityTargetSelectorNearestPlayer());
		EntityTargetSelector.registerTargetSelector("r", new EntityTargetSelectorRandomPlayer());
		// Register entity target binary operators
		EntityTargetBinaryOperator.registerOperator('&', 1000, new EntityTargetBinaryOperatorAnd());
		EntityTargetBinaryOperator.registerOperator('^', 2000, new EntityTargetBinaryOperatorXor());
		EntityTargetBinaryOperator.registerOperator('|', 3000, new EntityTargetBinaryOperatorOr());
	}

	public static @Nullable Integer getItemId(String itemIdOrNameAndMetadata) {
		// Get string without metadata part
		String itemIdOrNameString = itemIdOrNameAndMetadata;
		if (itemIdOrNameAndMetadata.contains(":")) itemIdOrNameString = itemIdOrNameAndMetadata.split(":")[0];
		// Parse id and return null if it is not a valid int or item name
		int id = 0;
		try {
			id = Integer.parseInt(Item.getItemByName(itemIdOrNameString));
		}
		catch (NumberFormatException e) {
			return null;
		}
		// Return null if the item id does not match to an item
		try {
			if (id < 0 || Item.itemsList[id] == null) return null;
		}
		catch (IndexOutOfBoundsException e) {
			return null;
		}
		// Return the valid id
		return id;
	}

	public static @Nullable Integer getItemMetadata(int id, String itemIdOrNameAndMetadata, int deafult) {
		// Get string metadata part
		if (!itemIdOrNameAndMetadata.contains(":")) return deafult;
		String metadataString = itemIdOrNameAndMetadata.split(":")[1];
		// Parse id
		int metadata = 0;
		try {
			metadata = Integer.parseInt(metadataString);
		}
		catch (NumberFormatException e) {
			return null;
		}
		// Validate metadata
		if (metadata < 0 || (Item.itemsList[id] instanceof ItemBlock && metadata > 15)) return null;
		// Return valid metadata
		return metadata;
	}

	/**
	 * Removes matching items from stacks.
	 * @param stacks The stacks to remove items from
	 * @param itemId Only items with this id are removed, all otherwise matching items are removed if null.
	 * @param itemMetadata Only items with this metadata are removed, all otherwise matching items are removed if null.
	 * @param itemsLeftToRemove The max amount of items to remove, null allows for infinite items to be removed. Decremented by the amount of items removed if not null.
	 * @return The total amount of items that where removed
	 */
	public static int removeItemsFromStacks(ItemStack[] stacks, @Nullable Integer itemId, @Nullable Integer itemMetadata, @Nullable AtomicInteger itemsLeftToRemove) {
		int itemsRemoved = 0;
		// For each item stack or until we have reached the limit of items to remove
		for (int stackIndex = 0; stackIndex < stacks.length && (itemsLeftToRemove == null || itemsLeftToRemove.get() > 0); stackIndex++) {
			ItemStack stack = stacks[stackIndex];
			// Skip if the stack is empty or it's item does not mach our restrictions.
			if (stack == null || (itemId != null && stack.itemID != itemId) || (itemMetadata != null && stack.itemDamage != itemMetadata)) continue;
			// Remove the whole stack if we have unlimited items to remove
			if (itemsLeftToRemove == null) {
				itemsRemoved += stack.stackSize;
				stacks[stackIndex] = null;
				continue;
			}
			// Remove the whole stack if the stack if we can without reaching the limit of items to remove
			if (stack.stackSize <= itemsLeftToRemove.get()) {
				itemsRemoved += stack.stackSize;
				itemsLeftToRemove.set(itemsLeftToRemove.get() - stack.stackSize);
				stacks[stackIndex] = null;
				continue;
			}
			// Else remove as many items from the stack as we can
			itemsRemoved += itemsLeftToRemove.get();
			stack.stackSize -= itemsLeftToRemove.get();
			itemsLeftToRemove.set(0);
		}
		return itemsRemoved;
	}
}
