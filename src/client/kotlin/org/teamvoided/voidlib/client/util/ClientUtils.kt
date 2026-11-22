package org.teamvoided.voidlib.client.util

import me.fzzyhmstrs.fzzy_config.nullCast
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.world.inventory.AbstractContainerMenu

fun getCurrentScreen() = Minecraft.getInstance().screen

fun getMenu(screen: Screen): AbstractContainerMenu? {
    return screen.nullCast<AbstractContainerScreen<*>>()?.menu
}
