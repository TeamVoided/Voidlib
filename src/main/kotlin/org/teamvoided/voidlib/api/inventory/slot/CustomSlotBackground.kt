package org.teamvoided.voidlib.api.inventory.slot

import net.minecraft.resources.ResourceLocation
import org.teamvoided.voidlib.Voidlib.id

interface CustomSlotBackground {

    fun getBackground(): ResourceLocation?

    companion object {

        val OUTSET_SLOT = id("container/slot/outset_slot")
        val EMPTY_SLOT = id("container/slot/empty_slot")

    }
}