@file:Suppress("unused")

package org.teamvoided.voidcore.helpers.mc

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text


// Builder Extensions
fun ItemGroup.Builder.icon(item: ItemConvertible): ItemGroup.Builder = this.icon { ItemStack(item) }
fun ItemGroup.Builder.translation(translation: String): ItemGroup.Builder = this.name(Text.translatable(translation))
fun ItemGroup.Builder.name(name: String): ItemGroup.Builder = this.name(Text.literal(name))



// Item Collector extensions
fun ItemGroup.ItemStackCollector.addItems(vararg items: ItemConvertible) {
    this.addItems(items.toList())
    return
}

fun ItemGroup.ItemStackCollector.addItems(list: Collection<ItemConvertible>) {
    this.addStacks(list.map { it.asItem().defaultStack })
}

fun ItemGroup.ItemStackCollector.addLists(vararg lists: Collection<ItemConvertible>) {
    this.addItems(lists.flatMap { it.map(ItemConvertible::asItem) })
}

// ItemGroupEvents Extensions
fun modifyTab(itemGroup: RegistryKey<ItemGroup>, modifyEntries: FabricItemGroupEntries.() -> Unit) {
    ItemGroupEvents.modifyEntriesEvent(itemGroup).register(modifyEntries)
}

fun FabricItemGroupEntries.addAfter(item: ItemConvertible, list: Collection<ItemConvertible>) {
    this.addAfter(item.asItem(), list.map { it.asItem().defaultStack })
}

fun FabricItemGroupEntries.addBefore(item: ItemConvertible, list: Collection<ItemConvertible>) =
    this.addBefore(item.asItem(), list.map { it.asItem().defaultStack })
