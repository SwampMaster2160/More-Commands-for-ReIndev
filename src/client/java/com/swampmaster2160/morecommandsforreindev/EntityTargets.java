package com.swampmaster2160.morecommandsforreindev;

import java.util.ArrayList;
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
			if (chr == '(' || chr == ')' || chr == '!' || chr == '|' || chr == '&' || chr == '^') {
				tokensAndParsedObjects.add("" + chr);
				continue;
			}
			// Else add the char to the token we are currently extracting
			currentToken = currentToken + chr;
			// Get the next char
			@Nullable Character nextChar = null;
			if (index < string.length() - 1) nextChar = string.charAt(index + 1);
			// If the next char should start a new token or we are at the end of the string then end the token
			if (nextChar == null || nextChar == '(' || nextChar == ')' || nextChar == '!' || nextChar == '|' || nextChar == '&' || nextChar == '^' || nextChar == '@' || nextChar == '#') {
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
		@Nullable Integer parseEnd = null;
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
				parseEnd = index;
				break;
			}
		}
		if (parseEnd == null) parseEnd = tokens.size() - 1;
		// Parse !
		for (int index = parseEnd; index >= startIndex; index--) {
			Object token = tokens.get(index);
			if (token instanceof String) {
				String tokenString = (String)token;
				if (tokenString.equals("!")) {
					tokens.remove(index);
					Object nextToken = null;
					try {
						nextToken = tokens.get(index);
					}
					catch (IndexOutOfBoundsException e) {
						return true;
					}
					tokens.remove(index);
					if (!(nextToken instanceof Entity[])) return true;
					Entity[] nextTokenEntities = (Entity[])nextToken;
					List<Entity> allEntities = world.getLoadedEntityList();
					ArrayList<Entity> notResult = new ArrayList<Entity>();
					for (Entity entity: allEntities) {
						boolean hasEntity = true;
						for (Entity nextEntity: nextTokenEntities) {
							if (nextEntity == entity) {
								hasEntity = false;
								break;
							}
						}
						if (hasEntity) notResult.add(entity);
					}
					tokens.add(index, notResult.toArray(new Entity[] {}));
				}
			}
		}
		// Parse &
		for (int index = startIndex; index < tokens.size(); index++) {
			Object token = tokens.get(index);
			if (token instanceof String) {
				String tokenString = (String)token;
				if (tokenString.equals("&")) {
					if (index == startIndex) return true;
					Object nextToken = null;
					try {
						nextToken = tokens.get(index + 1);
					}
					catch (IndexOutOfBoundsException e) {
						return true;
					}
					Object lastToken = null;
					try {
						lastToken = tokens.get(index - 1);
					}
					catch (IndexOutOfBoundsException e) {
						return true;
					}
					index--;
					tokens.remove(index);
					tokens.remove(index);
					tokens.remove(index);
					if (!(nextToken instanceof Entity[])) return true;
					if (!(lastToken instanceof Entity[])) return true;
					Entity[] nextTokenEntities = (Entity[])nextToken;
					Entity[] lastTokenEntities = (Entity[])lastToken;
					ArrayList<Entity> andResult = new ArrayList<Entity>();
					for (Entity entityA: lastTokenEntities) {
						boolean hasEntity = false;
						for (Entity entityB: nextTokenEntities) {
							if (entityB == entityA) {
								hasEntity = true;
								break;
							}
						}
						if (hasEntity) andResult.add(entityA);
					}
					tokens.add(index, andResult.toArray(new Entity[] {}));
				}
			}
		}
		// Parse ^
		for (int index = startIndex; index < tokens.size(); index++) {
			Object token = tokens.get(index);
			if (token instanceof String) {
				String tokenString = (String)token;
				if (tokenString.equals("^")) {
					if (index == startIndex) return true;
					Object nextToken = null;
					try {
						nextToken = tokens.get(index + 1);
					}
					catch (IndexOutOfBoundsException e) {
						return true;
					}
					Object lastToken = null;
					try {
						lastToken = tokens.get(index - 1);
					}
					catch (IndexOutOfBoundsException e) {
						return true;
					}
					index--;
					tokens.remove(index);
					tokens.remove(index);
					tokens.remove(index);
					if (!(nextToken instanceof Entity[])) return true;
					if (!(lastToken instanceof Entity[])) return true;
					Entity[] nextTokenEntities = (Entity[])nextToken;
					Entity[] lastTokenEntities = (Entity[])lastToken;
					ArrayList<Entity> xorResult = new ArrayList<Entity>();
					for (Entity entityA: lastTokenEntities) {
						boolean hasEntity = false;
						for (Entity entityB: nextTokenEntities) {
							if (entityB == entityA) {
								hasEntity = true;
								break;
							}
						}
						if (!hasEntity) xorResult.add(entityA);
					}
					for (Entity entityA: nextTokenEntities) {
						boolean hasEntity = false;
						for (Entity entityB: lastTokenEntities) {
							if (entityB == entityA) {
								hasEntity = true;
								break;
							}
						}
						if (!hasEntity) xorResult.add(entityA);
					}
					tokens.add(index, xorResult.toArray(new Entity[] {}));
				}
			}
		}
		// Parse |
		for (int index = startIndex; index < tokens.size(); index++) {
			Object token = tokens.get(index);
			if (token instanceof String) {
				String tokenString = (String)token;
				if (tokenString.equals("|")) {
					if (index == startIndex) return true;
					Object nextToken = null;
					try {
						nextToken = tokens.get(index + 1);
					}
					catch (IndexOutOfBoundsException e) {
						return true;
					}
					Object lastToken = null;
					try {
						lastToken = tokens.get(index - 1);
					}
					catch (IndexOutOfBoundsException e) {
						return true;
					}
					index--;
					tokens.remove(index);
					tokens.remove(index);
					tokens.remove(index);
					if (!(nextToken instanceof Entity[])) return true;
					if (!(lastToken instanceof Entity[])) return true;
					Entity[] nextTokenEntities = (Entity[])nextToken;
					Entity[] lastTokenEntities = (Entity[])lastToken;
					ArrayList<Entity> orResult = new ArrayList<Entity>();
					for (Entity entity: nextTokenEntities) {
						orResult.add(entity);
					}
					for (Entity entityA: lastTokenEntities) {
						boolean hasEntity = false;
						for (Entity entityB: nextTokenEntities) {
							if (entityB == entityA) {
								hasEntity = true;
								break;
							}
						}
						if (!hasEntity) orResult.add(entityA);
					}
					tokens.add(index, orResult.toArray(new Entity[] {}));
				}
			}
		}
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
		// Return tokens that cannot be converted
		if (token == "(" || token == ")" || token == "!" || token == "|" || token == "&" || token == "^") return token;
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
