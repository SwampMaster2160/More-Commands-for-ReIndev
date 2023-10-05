package com.swampmaster2160.morecommandsforreindev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.level.World;

public abstract class EntityTargets {
	public static @Nullable Entity[] getTargetsFromSelectorString(World world, String string, double x, double y, double z, Entity executerEntity) {
		ArrayList<Object> tokensAndParsedObjects = new ArrayList<Object>();
		// Loop over each char in the string to convert the string to tokens
		@Nullable String currentToken = "";
		for (int index = 0; index < string.length(); index++) {
			// Get the char
			char chr = string.charAt(index);
			// Add the char as a token if it is a single char token
			if (chr == '(' || chr == ')' || chr == '!' || EntityTargetBinaryOperator.isCharAnOperator(chr)) {
				tokensAndParsedObjects.add("" + chr);
				continue;
			}
			// Else add the char to the token we are currently extracting
			currentToken = currentToken + chr;
			// Get the next char
			@Nullable Character nextChar = null;
			if (index < string.length() - 1) nextChar = string.charAt(index + 1);
			// If the next char should start a new token or we are at the end of the string then end the token
			if (nextChar == null || nextChar == '(' || nextChar == ')' || nextChar == '!' || nextChar == '@' || nextChar == '#' || EntityTargetBinaryOperator.isCharAnOperator(nextChar)) {
				// If the token on its own can be converted to a list of entities then do so
				@Nullable Object tokenFirstRoundEvaluation = evaluateSingleToken(world, currentToken, x, y, z, executerEntity);
				// If there is an error then return
				if (tokenFirstRoundEvaluation == null) return null;
				// Add the tokens to the token list and create a new token
				tokensAndParsedObjects.add(tokenFirstRoundEvaluation);
				currentToken = "";
			}
		}
		// Parse all other unparsed tokens, if there is an error then return null
		boolean hasError = evaluateTokens(world, tokensAndParsedObjects, 0, true);
		if (hasError) return null;
		// Return the first token and should be only token
		if (tokensAndParsedObjects.size() != 1) return null;
		return (Entity[])(tokensAndParsedObjects.get(0));
	}

	public static boolean evaluateTokens(World world, ArrayList<Object> tokens, int startIndex, boolean isRoot) {
		// Find brackets and recursively parse the content of the brackets
		@Nullable Integer endIndex = null;
		for (int index = startIndex; index < tokens.size(); index++) {
			// Get token
			Object token = tokens.get(index);
			// Only the root evaluation should end without a closing bracket
			if (index == tokens.size() - 1 && !isRoot) {
				if (!(token instanceof String)) return true;
				if (!((String)token).equals(")")) return true;
			}
			// Skip tokens that have already been parsed
			if (!(token instanceof String)) continue;
			String tokenString = (String)token;
			// Opening brackets should call this function on the content
			if (tokenString.equals("(")) {
				// Remove opening bracket
				tokens.remove(index);
				if (index == tokens.size()) return true;
				// Evaluate bracketed area
				boolean hasError = evaluateTokens(world, tokens, index, false);
				if (hasError) return true;
				// Remove end bracket
				try {
					// Remove bracket token
					Object removed = tokens.remove(index + 1);
					// It is an error if the removed token is not a bracket
					if (!(removed instanceof String)) return true;
					if (!(((String)removed).equals(")"))) return true;
				}
				catch (IndexOutOfBoundsException e) {
					return true;
				}
			}
			// Closing brackets should end the bracket content or be an error in the root evaluation
			if (tokenString.equals(")")) {
				if (isRoot) return true;
				endIndex = index + 1;
				break;
			}
		}
		if (endIndex == null) endIndex = tokens.size();
		// Parse !
		// Go over each char in the parsing area in reverse
		for (int index = endIndex - 1; index >= startIndex; index--) {
			// Get the token as a string or return if it is not a string
			Object token = tokens.get(index);
			if (!(token instanceof String)) continue;
			String tokenString = (String)token;
			// Skip token if it is not a not operator
			if (!tokenString.equals("!")) continue;
			// Return if the operator is at the end of the parsing region
			if (!(index < endIndex - 1)) return true;
			// Remove not operator
			tokens.remove(index);
			// Pop out the operand
			Object nextToken = tokens.remove(index);
			// Get the operand as an entity list. It is an error if it is not so.
			if (!(nextToken instanceof Entity[])) return true;
			Entity[] nextTokenEntities = (Entity[])nextToken;
			// Get a list of all entities and create a result array;
			List<Entity> allEntities = world.getLoadedEntityList();
			ArrayList<Entity> notResult = new ArrayList<Entity>();
			// For each entity in the world
			for (Entity entity: allEntities) {
				// Add the world entity to the list if it is not in the operand list
				boolean hasEntity = true;
				for (Entity nextEntity: nextTokenEntities) {
					if (nextEntity == entity) {
						hasEntity = false;
						break;
					}
				}
				if (hasEntity) notResult.add(entity);
			}
			// Add the result list back to the tokens list
			tokens.add(index, notResult.toArray(new Entity[] {}));
			// In total we removed 2 tokens and added 1
			endIndex--;
		}
		// Parse binary operators
		// For each priority
		for (int priority: EntityTargetBinaryOperator.getPriorityIterator()) {
			// Get the operators that have said priority
			HashMap<Character, EntityTargetBinaryOperator> operatorsForPriority = EntityTargetBinaryOperator.getOperatorsForPriority(priority);
			// Loop over each token in the region to parse
			for (int index = startIndex; index < endIndex; index++) {
				// Get the token as a string or skip the token if it is not a string
				Object token = tokens.get(index);
				if (!(token instanceof String)) continue;
				String tokenString = (String)token;
				// Get the token as a char if the char length is 1 or else skip
				if (tokenString.length() != 1) continue;
				char tokenChar = tokenString.charAt(0);
				// Get the operator that the char is a symbol of or skip if there is no operator for it for this priority
				@Nullable EntityTargetBinaryOperator operator = operatorsForPriority.get(tokenChar);
				if (operator == null) continue;
				// Return if the operator is at the start or end of the parsing region
				if (!(index > startIndex && index < endIndex - 1)) return true;
				// Remove the operator token and point to the first operand of the operator
				tokens.remove(index);
				index--;
				// Pop out the operands and make sure they are entitity lists. It is an error if they are not.
				Object operandA = tokens.remove(index);
				Object operandB = tokens.remove(index);
				if (!((operandA instanceof Entity[]) && (operandB instanceof Entity[]))) return true;
				// Preform the operation and add it back to the list
				Entity[] result = operator.getResult(world, (Entity[])operandA, (Entity[])operandB);
				tokens.add(index, result);
				// In total, we removed 3 tokens and then added 1 token
				endIndex -= 2;
			}
		}
		// Returning false means there was no error
		return false;
	}

	/**
	 * Converts a token that does not depend on surrounding tokens to an entity list
	 * @param world The main world object
	 * @param token The token string to try to convert
	 * @param x The x pos of the executer
	 * @param y The y pos of the executer
	 * @param z The z pos of the executer
	 * @param executerEntity The entity that is executing the command
	 * @return An entity list if the token alone can be converted to one.
	 * null if there is an error converting the token.
	 * The input string if the input string cannot be converted.
	 */
	public static @Nullable Object evaluateSingleToken(World world, String token, double x, double y, double z, @Nullable Entity executerEntity) {
		// Tokens starting with @ are target selectors
		if (token.startsWith("@")) {
			String tokenWithoutPrefix = token.substring(1);
			// Get the selector
			EntityTargetSelector targetSelector = EntityTargetSelector.getRegisteredTargetSelector(tokenWithoutPrefix);
			if (targetSelector == null) return null;
			// Get entities that it selects
			return targetSelector.getSelectedEntities(world, x, y, z, executerEntity);
		}
		// Tokens starting with # select an entity by its instance id
		if (token.startsWith("#")) {
			// Parse the number
			String tokenWithoutPrefix = token.substring(1);
			int entityInstanceId;
			try {
				entityInstanceId = Integer.parseInt(tokenWithoutPrefix);
			}
			catch (NumberFormatException e) {
				return null;
			}
			// Get the entity with the id
			for (Entity entity: world.getLoadedEntityList()) {
				if (entity.entityId == entityInstanceId) return new Entity[] { entity };
			}
			return null;
		}
		// Else get the player by that name
		@Nullable Entity player = world.getPlayerEntityByName(token);
		if (player == null) return null;
		return new Entity[] { player };
	}
}
