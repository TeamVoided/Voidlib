@file:Suppress("unused")

package org.teamvoided.voidlib.util

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.level.ItemLike

fun CreativeModeTab.Output.acceptAll(vararg items: ItemLike) {
    acceptAll(items.map { it.asItem().defaultInstance })
}