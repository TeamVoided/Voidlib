package org.teamvoided.voidlib.client.util

import me.fzzyhmstrs.fzzy_config.nullCast
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.inventory.AbstractContainerMenu
import org.teamvoided.voidlib.util.ifMod

// Built in Resource Pack
fun registerBuiltInResourcePack(
    modId: String,
    id: ResourceLocation,
    packType: ResourcePackActivationType = ResourcePackActivationType.NORMAL,
) {
    ifMod(modId) {
        assert(ResourceManagerHelper.registerBuiltinResourcePack(id, it, packType)) {
            "Failed to register built-in pack: '$id' for mod: '$modId'"
        }
    }
}

fun registerBuiltInResourcePack(
    modId: String,
    id: ResourceLocation,
    name: Component,
    packType: ResourcePackActivationType = ResourcePackActivationType.NORMAL,
) {
    ifMod(modId) {
        assert(ResourceManagerHelper.registerBuiltinResourcePack(id, it, name, packType)) {
            "Failed to register built-in pack: '$id' for mod: '$modId'"
        }
    }
}


// Screen
fun getCurrentScreen(): Screen? = Minecraft.getInstance().screen

fun getMenu(screen: Screen?): AbstractContainerMenu? {
    return screen.nullCast<AbstractContainerScreen<*>>()?.menu
}

