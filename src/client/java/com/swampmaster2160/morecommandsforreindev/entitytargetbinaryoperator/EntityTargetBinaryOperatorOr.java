package com.swampmaster2160.morecommandsforreindev.entitytargetbinaryoperator;

import java.util.ArrayList;

import com.swampmaster2160.morecommandsforreindev.EntityTargetBinaryOperator;

import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.level.World;

public class EntityTargetBinaryOperatorOr extends EntityTargetBinaryOperator {
	@Override
	public Entity[] getResult(World world, Entity[] a, Entity[] b) {
		ArrayList<Entity> orResult = new ArrayList<Entity>();
		for (Entity entity: a) {
			orResult.add(entity);
		}
		for (Entity entityB: b) {
			boolean hasEntity = false;
			for (Entity entityA: a) {
				if (entityA == entityB) {
					hasEntity = true;
					break;
				}
			}
			if (!hasEntity) orResult.add(entityB);
		}
		return orResult.toArray(new Entity[] {});
	}
}
