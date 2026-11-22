package org.teamvoided.voidlib.mixin.testmod;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.voidlib.testmod.inv.BgSlot;

@Mixin(AbstractContainerMenu.class)
public class AbstractContainerMenuMixin {

    @Shadow
    @Final
    public NonNullList<Slot> slots;

    @Inject(method = "addSlot", at = @At(value = "HEAD"))
    void swapSlot(Slot slot, CallbackInfoReturnable<Slot> cir, @Local(argsOnly = true) LocalRef<Slot> slotRef) {
        if (slot.container instanceof Inventory && slots.size() == 12) {
            slotRef.set(new BgSlot(slot.container, 12, slot.x, slot.y));
        }
    }

}
