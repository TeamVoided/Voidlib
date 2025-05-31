package org.teamvoided.headless.mixin;

import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.client.render.block.entity.model.AbstractSkullBlockEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.headless.HeadlessRegistry;
import org.teamvoided.headless.api.SkullModelWithAttachment;

import java.util.Map;

@Mixin(SkullBlockEntityRenderer.class)
public abstract class SkullBlockEntityRendererMixin {
    @Inject(method = "getModels", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;", remap = false))
    private static void addCustomModels(EntityModelLoader modelLoader, CallbackInfoReturnable<Map<SkullBlock.SkullType, AbstractSkullBlockEntityModel>> cir,
                                        @Local ImmutableMap.Builder<SkullBlock.SkullType, AbstractSkullBlockEntityModel> builder) {
        HeadlessRegistry.SKULLS.forEach((type, model) -> builder.put(type, model.invoke(modelLoader)));
    }

    @Inject(method = "renderSkull", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/entity/model/AbstractSkullBlockEntityModel;method_60879(Lnet/minecraft/client/util/math/MatrixStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;II)V", shift = At.Shift.AFTER))
    private static void renderSkullModelAttachment(Direction direction, float yaw, float animationProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractSkullBlockEntityModel model, RenderLayer renderLayer, CallbackInfo ci) {
        if (model instanceof SkullModelWithAttachment attachment) {
            attachment.renderAttachment(direction, yaw, animationProgress, matrices, vertexConsumers, light, model, renderLayer);
        }
    }
}