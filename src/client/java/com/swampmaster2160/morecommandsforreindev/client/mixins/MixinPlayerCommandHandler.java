package com.swampmaster2160.morecommandsforreindev.client.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.swampmaster2160.morecommandsforreindev.commands.CommandKill;
import com.swampmaster2160.morecommandsforreindev.commands.CommandListEntities;
import com.swampmaster2160.morecommandsforreindev.commands.CommandNewTP;
import com.swampmaster2160.morecommandsforreindev.commands.CommandSeed;
import com.swampmaster2160.morecommandsforreindev.commands.CommandSetWorldSpawn;
import com.swampmaster2160.morecommandsforreindev.commands.CommandSummon;
import com.swampmaster2160.morecommandsforreindev.commands.CommandToggleDownFall;
import com.swampmaster2160.morecommandsforreindev.commands.CommandWorldInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.mitask.PlayerCommandHandler;
import net.minecraft.mitask.command.Command;

@Mixin(PlayerCommandHandler.class)
public abstract class MixinPlayerCommandHandler {
	// Injects registering new commands.
	@Inject(method = "registerCommands", at = @At("TAIL"), cancellable = true)
	private void registerCommands(Minecraft mc, CallbackInfo info) {
		((PlayerCommandHandler)(Object)this).addCommand(new CommandWorldInfo(((PlayerCommandHandler)(Object)this)));
		((PlayerCommandHandler)(Object)this).addCommand(new CommandSeed(((PlayerCommandHandler)(Object)this)));
		((PlayerCommandHandler)(Object)this).addCommand(new CommandSetWorldSpawn(((PlayerCommandHandler)(Object)this)));
		((PlayerCommandHandler)(Object)this).addCommand(new CommandToggleDownFall(((PlayerCommandHandler)(Object)this)));
		((PlayerCommandHandler)(Object)this).addCommand(new CommandSummon(((PlayerCommandHandler)(Object)this)));
		((PlayerCommandHandler)(Object)this).addCommand(new CommandKill(((PlayerCommandHandler)(Object)this)));
		((PlayerCommandHandler)(Object)this).addCommand(new CommandListEntities(((PlayerCommandHandler)(Object)this)));
		replaceCommand(new CommandNewTP(((PlayerCommandHandler)(Object)this)));
	}

	public void replaceCommand(Command command) {
		for(Command oldCommand : PlayerCommandHandler.commands) {
			if (oldCommand.getName() != command.getName()) continue;
			PlayerCommandHandler.commands.remove(oldCommand);
			break;
		}
		((PlayerCommandHandler)(Object)this).addCommand(command);
	}
}
