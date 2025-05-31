@file:Suppress("unused")

package org.teamvoided.headless

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.minecraft.client.render.block.entity.model.AbstractSkullBlockEntityModel
import net.minecraft.client.render.block.entity.model.SkullBlockEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelLoader
import net.minecraft.util.Identifier
import org.teamvoided.headless.block.HeadlessSkullType
import org.teamvoided.headless.mixin.SkullBlockEntityRendererAccessor
import org.teamvoided.headless.render.block.entity.model.TwoLayerSkullModel

object HeadlessRegistry {
    @JvmField
    internal val SKULLS = mutableMapOf<HeadlessSkullType, (EntityModelLoader) -> AbstractSkullBlockEntityModel>()

    fun register(
        type: HeadlessSkullType, texture: Identifier, model: (EntityModelLoader) -> AbstractSkullBlockEntityModel,
    ) {
        SkullBlockEntityRendererAccessor.headless_TEXTURES()[type] = texture
        SKULLS[type] = model
    }

    fun registerBasicHead(type: HeadlessSkullType, texture: Identifier) {
        val model = registerLayer(type.type.extendPath("_head"), "main")
        addLayer(model, SkullBlockEntityModel::getHeadTexturedModelData)
        SkullBlockEntityRendererAccessor.headless_TEXTURES()[type] = texture
        SKULLS[type] = { SkullBlockEntityModel(it.getModelPart(model)) }
    }

    fun registerTwoLayerSkull(type: HeadlessSkullType, texture: Identifier, overlayTexture: Identifier) {
        val head = registerLayer(type.type.extendPath("_head"), "main")
        val headOverlay = registerLayer(type.type.extendPath("_head"), "overlay")
        addLayer(head, TwoLayerSkullModel::getSkullTexturedModelData)
        addLayer(headOverlay, TwoLayerSkullModel::getSkullTexturedModelOverlayData)

        SkullBlockEntityRendererAccessor.headless_TEXTURES()[type] = texture
        SKULLS[type] = { TwoLayerSkullModel(it.getModelPart(head), it.getModelPart(headOverlay), overlayTexture) }
    }

    private fun registerLayer(id: Identifier, layer: String): EntityModelLayer = EntityModelLayer(id, layer)
    private fun addLayer(layer: EntityModelLayer, provider: EntityModelLayerRegistry.TexturedModelDataProvider) =
        EntityModelLayerRegistry.registerModelLayer(layer, provider)
}