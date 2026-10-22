package org.teamvoided.voidlib.mixin.testmod;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.voidlib.testmod.inv.BgSlot;

@Mixin(AbstractContainerMenu.class)
public class AbstractContainerMenuMixin {

    @WrapOperation(method = "addInventoryExtendedSlots", at = @At(value = "NEW", target = "(Lnet/minecraft/world/Container;III)Lnet/minecraft/world/inventory/Slot;"))
    Slot swapSlot(Container container, int idx, int j, int k, Operation<Slot> original) {
        if (idx % 4 == 0) {
            return new BgSlot(container, idx, j, k);
        }
        return original.call(container, idx, j, k);
    }

}
