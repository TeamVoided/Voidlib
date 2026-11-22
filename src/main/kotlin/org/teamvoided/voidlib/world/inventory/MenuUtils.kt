package org.teamvoided.voidlib.world.inventory

import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType

fun getType(menu: AbstractContainerMenu): MenuType<*>? {
    return try {
        menu.type
    } catch (_: UnsupportedOperationException) {
        null
    }
}

fun getTypeHolder(menu: AbstractContainerMenu): Holder<MenuType<*>>? {
    return getType(menu)?.let(BuiltInRegistries.MENU::wrapAsHolder)
}
