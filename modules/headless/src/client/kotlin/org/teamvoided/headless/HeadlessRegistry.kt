@file:Suppress("unused")

package org.teamvoided.headless

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.minecraft.client.render.block.entity.model.AbstractSkullBlockEntityModel
import net.minecraft.client.render.block.entity.model.SkullBlockEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelLoader
import net.minecraft.util.Identifier
import org.teamvoided.headless.Headless.id
import org.teamvoided.headless.mixin.SkullBlockEntityRendererAccessor
import org.teamvoided.headless.render.block.entity.model.SkullWithOverlayModel
import org.teamvoided.headless.skull.CustomSkullType

object HeadlessRegistry {
    @JvmField
    internal val SKULLS = mutableMapOf<CustomSkullType, (EntityModelLoader) -> AbstractSkullBlockEntityModel>()

    fun register(
        type: CustomSkullType,
        texture: Identifier,
        model: (EntityModelLoader) -> AbstractSkullBlockEntityModel,
    ) {
        SkullBlockEntityRendererAccessor.headless_TEXTURES()[type] = texture
        SKULLS[type] = model
    }

    fun registerHead(
        type: CustomSkullType,
        texture: Identifier,
    ) {
        val model = registerLayer("${type.type}_head", "main")
        EntityModelLayerRegistry.registerModelLayer(model, SkullBlockEntityModel::getHeadTexturedModelData)
        SkullBlockEntityRendererAccessor.headless_TEXTURES()[type] = texture
        SKULLS[type] = { SkullBlockEntityModel(it.getModelPart(model)) }
    }

    @ExperimentalStdlibApi
    fun registerLayeredSkull(type: CustomSkullType, texture: Identifier, overlayTexture: Identifier) {
        val head = registerLayer("${type.type}_head", "main")
        val headOverlay = registerLayer("${type.type}_head", "overlay")
        EntityModelLayerRegistry.registerModelLayer(head, SkullWithOverlayModel::getSkullTexturedModelData)
        EntityModelLayerRegistry.registerModelLayer(
            headOverlay,
            SkullWithOverlayModel::getSkullTexturedModelOverlayData
        )

        SkullBlockEntityRendererAccessor.headless_TEXTURES()[type] = texture
        SKULLS[type] = { SkullWithOverlayModel(it.getModelPart(head), it.getModelPart(headOverlay), overlayTexture) }
    }

    private fun registerLayer(id: String, layer: String): EntityModelLayer = EntityModelLayer(id(id), layer)
}