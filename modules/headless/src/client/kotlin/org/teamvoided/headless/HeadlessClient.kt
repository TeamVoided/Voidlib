package org.teamvoided.headless

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.minecraft.client.render.block.entity.model.SkullBlockEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.util.Identifier
import org.teamvoided.headless.Headless.id
import org.teamvoided.headless.render.block.entity.model.SkullWithOverlayModel
import org.teamvoided.headless.skull.SkullInit

@Suppress("unused")
object HeadlessClient {
    val HUSK_HEAD = register("husk_head", "main")
    val husk_png = Identifier.ofDefault("textures/entity/zombie/husk.png")

    val BOGGED_HEAD = register("bogged_head", "main")
    val BOGGED_HEAD_OVERLAY = register("bogged_head", "overlay")
    val bogged_png = Identifier.ofDefault("textures/entity/skeleton/bogged.png")
    val bogged_png2 = Identifier.ofDefault("textures/entity/skeleton/bogged_overlay.png")

    val stray_png = Identifier.ofDefault("textures/entity/skeleton/stray.png")
    val stray_png2 = Identifier.ofDefault("textures/entity/skeleton/stray_overlay.png")

    @OptIn(ExperimentalStdlibApi::class)
    fun init() {
        EntityModelLayerRegistry.registerModelLayer(HUSK_HEAD, SkullBlockEntityModel::getHeadTexturedModelData)
        HeadlessRegistry.register(SkullInit.HUSK, husk_png) { SkullBlockEntityModel(it.getModelPart(HUSK_HEAD)) }

        EntityModelLayerRegistry.registerModelLayer(BOGGED_HEAD, SkullWithOverlayModel::getSkullTexturedModelData)
        EntityModelLayerRegistry
            .registerModelLayer(BOGGED_HEAD_OVERLAY, SkullWithOverlayModel::getSkullTexturedModelOverlayData)
        HeadlessRegistry.register(SkullInit.BOGGED, bogged_png) {
            SkullWithOverlayModel(it.getModelPart(BOGGED_HEAD), it.getModelPart(BOGGED_HEAD_OVERLAY), bogged_png2)
        }

        HeadlessRegistry.registerLayeredSkull(SkullInit.STRAY, stray_png, stray_png2)
    }

    private fun register(id: String, layer: String): EntityModelLayer = EntityModelLayer(id(id), layer)
}
