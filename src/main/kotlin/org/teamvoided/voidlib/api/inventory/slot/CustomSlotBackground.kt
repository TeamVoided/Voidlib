package org.teamvoided.voidlib.api.inventory.slot

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.inventory.Slot
import org.teamvoided.voidlib.Voidlib.id

/**
 * Interface for slots to add a custom background image. Unlike [Slot.getNoItemIcon] this is always rendered
 */
interface CustomSlotBackground {

    fun getBackground(): ResourceLocation?

    companion object {

        val OUTSET_SLOT = id("container/slot/outset_slot")
        val EMPTY_SLOT = id("container/slot/empty_slot")

    }
}