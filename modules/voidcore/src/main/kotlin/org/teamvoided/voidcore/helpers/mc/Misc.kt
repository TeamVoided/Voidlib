@file:Suppress("unused")

package org.teamvoided.voidcore.helpers.mc

import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraft.loot.LootTable
import net.minecraft.registry.RegistryKey
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Hand

fun Hand.toSlot() = if (this == Hand.MAIN_HAND) EquipmentSlot.MAINHAND else EquipmentSlot.OFFHAND

fun ItemStack.hasEnchantment(enchantment: RegistryKey<Enchantment>): Boolean =
    this.enchantments.enchantments.any { it.isRegistryKey(enchantment) }

fun ServerWorld.getLootTable(key: RegistryKey<LootTable>): LootTable = this.server.method_58576().getLootTable(key)