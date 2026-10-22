package org.teamvoided.voidlib.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.voidlib.api.inventory.slot.InventoryOpenMenu;

@Mixin(AbstractContainerMenu.class)
public abstract class AbstractContainerMenuMixin {

    @Inject(method = "removed", at = @At("HEAD"))
    void updateWhenRemoved(Player player, CallbackInfo ci) {
        if (this instanceof InventoryOpenMenu) {
            player.inventoryMenu.resumeRemoteUpdates();
        }
    }

}