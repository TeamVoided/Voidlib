@file:Suppress("unused")

package org.teamvoided.voidcore.mc.item

import net.minecraft.block.Block
import net.minecraft.item.AxeItem
import net.minecraft.item.ToolMaterials

abstract class StrippedBlocks : AxeItem(ToolMaterials.WOOD, Settings()) {
    companion object {
        fun map(): Map<Block, Block> = STRIPPED_BLOCKS
    }
}