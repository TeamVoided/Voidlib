package org.teamvoided.voidlib.api.event

import net.minecraft.world.level.block.Block
import org.teamvoided.voidlib.Voidlib.log
import org.teamvoided.voidlib.impl.event.BlockStrippingRegistryIml

object BlockStrippingRegistry {

    @JvmStatic
    fun register(input: Block, stripped: Block) {
        val old = BlockStrippingRegistryIml.BLOCK_STATE_MAP.put(input, stripped)
        if (old != null) {
            log.debug("Replaced old stripping mapping from {} to {} with {}", input, old, stripped)
        }
    }

}