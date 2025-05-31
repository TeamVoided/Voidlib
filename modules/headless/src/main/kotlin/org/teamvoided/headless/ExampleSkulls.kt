package org.teamvoided.headless

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.SkullItem
import net.minecraft.registry.Registries
import org.teamvoided.headless.block.HeadlessSkull
import org.teamvoided.headless.block.HeadlessSkullType
import org.teamvoided.headless.block.HeadlessWallSkull
import org.teamvoided.voidcore.helpers.mc.register

object ExampleSkulls {
    val skulls = mutableSetOf<SkullData>()

    val HUSK_LIKE = makeSkull("husk_like")
    val BOGGED_LIKE = makeSkull("bogged_like")
    val STRAY_LIKE = makeSkull("stray_like")
    val TESTING = makeSkull("testing")
    val MIXING = makeSkull("mixing")

    internal fun init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS)
            .register { skulls.forEach { data -> it.prepend(data.item) } }
    }

    private fun makeSkull(name: String): SkullData {
        val skull = HeadlessSkullType(Headless.id(name))
        val block = Registries.BLOCK.register(
            Headless.id("${name}_skull"),
            HeadlessSkull(skull, AbstractBlock.Settings.copy(Blocks.SKELETON_SKULL))
        )
        val wallBlock = Registries.BLOCK.register(
            Headless.id("${name}_wall_skull"),
            HeadlessWallSkull(skull, AbstractBlock.Settings.copy(block))
        )
        val item = Registries.ITEM.register(Headless.id("${name}_skull"), SkullItem(block, wallBlock, Item.Settings()))

        val data = SkullData(skull, block, wallBlock, item)
        skulls.add(data)
        return data
    }

    data class SkullData(val skull: HeadlessSkullType, val block: Block, val wallBlock: Block, val item: Item)
}
