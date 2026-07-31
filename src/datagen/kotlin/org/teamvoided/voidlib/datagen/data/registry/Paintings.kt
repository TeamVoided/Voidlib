package org.teamvoided.voidlib.datagen.data.registry

import net.minecraft.ChatFormatting
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.decoration.painting.PaintingVariant
import java.util.*

object Paintings : RegistryBootstrapper<PaintingVariant> {

    override fun BootstrapContext<PaintingVariant>.init() {
//        painting(VPaintings.QUESTIONABLE, 1, 1, true)
    }

    @Suppress("unused")
    fun BootstrapContext<PaintingVariant>.painting(
        key: ResourceKey<PaintingVariant>,
        width: Int,
        height: Int,
        hasAuthor: Boolean,
    ) {
        register(
            key, PaintingVariant(
                width, height, key.identifier(),
                Optional.of(key.langKey("title", ChatFormatting.YELLOW)),
                if (hasAuthor) Optional.of(key.langKey("author", ChatFormatting.GRAY)) else Optional.empty()
            )
        )
    }

    fun ResourceKey<PaintingVariant>.langKey(lineName: String, color: ChatFormatting): MutableComponent =
        Component.translatable(identifier().toLanguageKey("painting", lineName)).withStyle(color)

}