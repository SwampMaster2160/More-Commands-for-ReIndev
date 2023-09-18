package com.swampmaster2160.gamerulesforreindev.client.mixins;

import net.minecraft.src.game.level.WorldInfo;
import net.minecraft.src.game.nbt.NBTTagCompound;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldInfo.class)
public class MixinWorldInfo {
	private int testInt;

	/*@Inject(method = "WorldInfo", at = @At("HEAD"), cancellable = true)
	public void WorldInfo(NBTTagCompound nBTTagCompound, CallbackInfoReturnable<WorldInfo> cir) {
		System.out.println("Hello World A!");
	}

	@Inject(method = "getNBTTagCompound", at = @At("HEAD"), cancellable = true)
	public void mixin(CallbackInfoReturnable<NBTTagCompound> cir) {
		System.out.println("Hello World B!");
	}*/

	@Inject(method = "<init>", at = @At("TAIL"), cancellable = true)
	public void WorldInfo(NBTTagCompound nBTTagCompound, CallbackInfo info) {
		//this.testInt = 
		System.out.println("Hello World A!");
	}

	@Inject(method = "getPlayerNBTTagCompound", at = @At("HEAD"), cancellable = true)
	public void getPlayerNBTTagCompound(CallbackInfoReturnable<NBTTagCompound> cir) {
		System.out.println(testInt);
	}

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
		if (!world.multiplayerWorld && world.getBlockId(x, y - 1, z) ==
				ExampleMod.ratBlock.getRegisteredBlockId()) {
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
