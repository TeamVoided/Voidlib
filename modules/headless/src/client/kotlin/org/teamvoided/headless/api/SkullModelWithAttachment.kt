package org.teamvoided.headless.api

import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.model.AbstractSkullBlockEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.Direction

interface SkullModelWithAttachment {
    fun renderAttachment(
        direction: Direction?,
        yaw: Float,
        animationProgress: Float,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        model: AbstractSkullBlockEntityModel,
        renderLayer: RenderLayer,
    )
}