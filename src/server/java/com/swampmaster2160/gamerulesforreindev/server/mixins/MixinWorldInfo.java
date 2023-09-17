package com.swampmaster2160.gamerulesforreindev.server.mixins;

import net.minecraft.src.game.level.WorldInfo;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(WorldInfo.class)
public class MixinWorldInfo {

	/*@Inject(method = "blockActivated", at = @At("HEAD"), cancellable = true)
	public void onBlockActivated(World world, int x, int y, int z, EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
		if (world.getBlockId(x, y - 1, z) == ExampleMod.ratBlock.getRegisteredBlockId()) {
			int loot = ExampleMod.randomLootId();
			if (loot != 0) {
				EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(loot, 1));
				world.entityJoinedWorld(item);
			}
			int i = world.getBlockMetadata(x, y, z);
			if (i >= 6) {
				world.setBlockWithNotify(x, y, z, 0);
			} else {
				world.setBlockMetadataWithNotify(x, y, z, i + 1);
				world.markBlockAsNeedsUpdate(x, y, z);
			}
			world.playSoundEffect(x + 0.5f, y + 0.5f, z + 0.5f, "random.pop", 1f, 1f);
			cir.setReturnValue(Boolean.TRUE);
		}
	}

	@Override
	public void onBlockPlaced(World world, int x, int y, int z, int blockFace) {
		if (world.getBlockId(x, y - 1, z) == ExampleMod.ratBlock.getRegisteredBlockId()) {
		   int i = world.getBlockMetadata(x, y, z);
		   if (i >= 6) {
			   world.setBlockWithNotify(x, y, z, 0);
		   } else {
			   world.setBlockMetadataWithNotify(x, y, z, i + 1);
			   world.markBlockAsNeedsUpdate(x, y, z);
		   }
		   world.playSoundEffect(x + 0.5f, y + 0.5f, z + 0.5f, "random.pop", 1f, 1f);
		}
	}*/
}
