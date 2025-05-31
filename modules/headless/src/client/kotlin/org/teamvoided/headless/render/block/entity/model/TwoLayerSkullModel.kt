package org.teamvoided.headless.render.block.entity.model

import net.minecraft.client.model.*
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.model.AbstractSkullBlockEntityModel
import net.minecraft.client.render.block.entity.model.SkullBlockEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction
import org.teamvoided.headless.api.SkullModelWithAttachment

open class TwoLayerSkullModel(root: ModelPart, hat: ModelPart, layer: Identifier) : SkullBlockEntityModel(root),
    SkullModelWithAttachment {
    open val overlayLayer: RenderLayer = RenderLayer.getEntityCutoutNoCullZOffset(layer)
    open val hat: ModelPart = hat.getChild("hat")

    override fun setHeadAngles(animationProgress: Float, yaw: Float, pitch: Float) {
        super.setHeadAngles(animationProgress, yaw, pitch)
        hat.yaw = yaw * 0.017453292f
        hat.pitch = pitch * 0.017453292f
    }

    override fun renderAttachment(
        direction: Direction?, yaw: Float,
        animationProgress: Float, matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider, light: Int,
        model: AbstractSkullBlockEntityModel, renderLayer: RenderLayer,
    ) {
        hat.render(matrices, vertexConsumers.getBuffer(overlayLayer), light, OverlayTexture.DEFAULT_UV)
    }

    companion object {
        fun getSkullTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            modelPartData.addChild(
                "head",
                ModelPartBuilder.create()
                    .uv(0, 0)
                    .cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f),
                ModelTransform.NONE
            )
            return TexturedModelData.of(modelData, 64, 32)
        }

        fun getSkullTexturedModelOverlayData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            modelPartData.addChild(
                "hat",
                ModelPartBuilder.create()
                    .uv(0, 0)
                    .cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, Dilation(0.25f)),
                ModelTransform.NONE
            )
            return TexturedModelData.of(modelData, 64, 32)
        }
    }
}