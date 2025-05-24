@file:Suppress("unused")

package org.teamvoided.voidcore.mc.item

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.ShovelItem
import net.minecraft.item.ToolMaterials

abstract class PathStates : ShovelItem(ToolMaterials.WOOD, Settings()) {
    companion object {
        fun map(): Map<Block, BlockState> = PATH_STATES
    }
}