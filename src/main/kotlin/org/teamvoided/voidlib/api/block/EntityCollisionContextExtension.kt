package org.teamvoided.voidlib.api.block

import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

interface EntityCollisionContextExtension {

    fun isHoldingItem(tag: TagKey<Item>): Boolean

    fun setRecursive(state: Boolean)

    fun isRecursive(): Boolean

}