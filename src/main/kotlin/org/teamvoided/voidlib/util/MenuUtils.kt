package org.teamvoided.voidlib.util

import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType

fun getType(containerMenu: AbstractContainerMenu): MenuType<*>? {
    try {
        return containerMenu.type
    } catch (_: Exception) {
    }
    return null
}

fun getTypeHolder(containerMenu: AbstractContainerMenu): Holder<MenuType<*>>? {
    return getType(containerMenu)?.let(BuiltInRegistries.MENU::wrapAsHolder)
}