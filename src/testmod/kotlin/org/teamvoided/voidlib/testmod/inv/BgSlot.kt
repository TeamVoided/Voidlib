package org.teamvoided.voidlib.testmod.inv

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.Container
import net.minecraft.world.inventory.Slot
import org.teamvoided.voidlib.api.inventory.slot.CustomSlotBackground

class BgSlot(container: Container, i: Int, j: Int, k: Int) : Slot(container, i, j, k), CustomSlotBackground {

    override fun getBackground(): ResourceLocation {
        return if (item.isEmpty)
            CustomSlotBackground.EMPTY_SLOT
        else
            CustomSlotBackground.OUTSET_SLOT
    }

}