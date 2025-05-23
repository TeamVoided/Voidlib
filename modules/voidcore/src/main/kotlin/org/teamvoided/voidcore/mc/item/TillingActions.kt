@file:Suppress("unused")

package org.teamvoided.voidcore.mc.item

import com.mojang.datafixers.util.Pair
import net.minecraft.block.Block
import net.minecraft.item.HoeItem
import net.minecraft.item.ItemUsageContext
import net.minecraft.item.ToolMaterials
import java.util.function.Consumer
import java.util.function.Predicate

abstract class TillingActions : HoeItem(ToolMaterials.WOOD, Settings()) {
    companion object {
        fun map(): Map<Block, Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>>> = TILLING_ACTIONS
    }
}