package org.teamvoided.headless.skull

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.SkullItem
import net.minecraft.registry.Registries
import org.teamvoided.headless.Headless.id
import org.teamvoided.headless.block.HeadlessSkull
import org.teamvoided.headless.block.HeadlessWallSkull
import org.teamvoided.headless.utils.register

object SkullInit {
    val HUSK = CustomSkullType("husk")
    val BOGGED = CustomSkullType("bogged")
    val STRAY = CustomSkullType("stray")


    val HUSK_SKULL_BLOCK =
        block("husk_skull", HeadlessSkull(HUSK, copy(Blocks.SKELETON_SKULL)))
    val HUSK_SKULL_WALL_BLOCK =
        block("husk_wall_skull", HeadlessWallSkull(HUSK, copy(Blocks.SKELETON_SKULL)))
    val HUSK_SKULL_ITEM =
        item("husk_skull", SkullItem(HUSK_SKULL_BLOCK, HUSK_SKULL_WALL_BLOCK, Item.Settings()))

    val BOGGED_SKULL_BLOCK =
        block("bogged_skull", HeadlessSkull(BOGGED, copy(Blocks.SKELETON_SKULL)))
    val BOGGED_SKULL_WALL_BLOCK =
        block("bogged_wall_skull", HeadlessWallSkull(BOGGED, copy(Blocks.SKELETON_SKULL)))
    val BOGGED_SKULL_ITEM =
        item("bogged_skull", SkullItem(BOGGED_SKULL_BLOCK, BOGGED_SKULL_WALL_BLOCK, Item.Settings()))

    val STRAY_SKULL_BLOCK =
        block("stray_skull", HeadlessSkull(STRAY, copy(Blocks.SKELETON_SKULL)))
    val STRAY_SKULL_WALL_BLOCK =
        block("stray_wall_skull", HeadlessWallSkull(STRAY, copy(Blocks.SKELETON_SKULL)))
    val STRAY_SKULL_ITEM =
        item("stray_skull", SkullItem(STRAY_SKULL_BLOCK, STRAY_SKULL_WALL_BLOCK, Item.Settings()))

    fun block(name: String, block: Block) = Registries.BLOCK.register(id(name), block)
    fun item(name: String, item: Item) = Registries.ITEM.register(id(name), item)

    fun init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register {
            it.prepend(HUSK_SKULL_ITEM)
            it.prepend(BOGGED_SKULL_ITEM)
            it.prepend(STRAY_SKULL_ITEM)
        }
    }
}