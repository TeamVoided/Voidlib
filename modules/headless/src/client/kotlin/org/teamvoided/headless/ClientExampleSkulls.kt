package org.teamvoided.headless

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.minecraft.client.render.block.entity.model.SkullBlockEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import org.teamvoided.headless.Headless.id
import org.teamvoided.headless.render.block.entity.model.TwoLayerSkullModel
import org.teamvoided.voidcore.helpers.mc.mc

object ClientExampleSkulls {
    // Layers
    val HUSK_HEAD = make("husk_head", "main")
    val BOGGED_HEAD = make("bogged_head", "main")
    val BOGGED_HEAD_OVERLAY = make("bogged_head", "overlay")

    // Textures
    val CREEPER = mc("textures/entity/creeper/creeper.png")
    val CREEPER_ARMOR = mc("textures/entity/creeper/creeper_armor.png")
    val HUSK = mc("textures/entity/zombie/husk.png")
    val BOGGED = mc("textures/entity/skeleton/bogged.png")
    val BOGGED_OVERLAY = mc("textures/entity/skeleton/bogged_overlay.png")
    val WITHER = mc("textures/entity/skeleton/wither_skeleton.png")
    val STRAY = mc("textures/entity/skeleton/stray.png")
    val STRAY_OVERLAY = mc("textures/entity/skeleton/stray_overlay.png")
    val SUNNY = mc("textures/entity/player/wide/sunny.png")

    fun initExampleSkulls() {
        // Registering a skull
        addLayer(HUSK_HEAD, SkullBlockEntityModel::getHeadTexturedModelData)
        HeadlessRegistry.register(ExampleSkulls.HUSK_LIKE.skull, HUSK) { SkullBlockEntityModel(it.getModelPart(HUSK_HEAD)) }

        // Registering a basic skull (eg. player, zombie, husk)
        HeadlessRegistry.registerBasicHead(ExampleSkulls.TESTING.skull, SUNNY)

        // Registering a Two Layer skull
        addLayer(BOGGED_HEAD, TwoLayerSkullModel::getSkullTexturedModelData)
        addLayer(BOGGED_HEAD_OVERLAY, TwoLayerSkullModel::getSkullTexturedModelOverlayData)
        HeadlessRegistry.register(ExampleSkulls.BOGGED_LIKE.skull, BOGGED) {
            TwoLayerSkullModel(it.getModelPart(BOGGED_HEAD), it.getModelPart(BOGGED_HEAD_OVERLAY), BOGGED_OVERLAY)
        }

        // Registering a Basic Two Layer skull (eg. Stray, Bogged)
        HeadlessRegistry.registerTwoLayerSkull(ExampleSkulls.STRAY_LIKE.skull, STRAY, STRAY_OVERLAY)
        HeadlessRegistry.registerTwoLayerSkull(ExampleSkulls.MIXING.skull, WITHER, CREEPER_ARMOR)
    }

    private fun addLayer(layer: EntityModelLayer, provider: EntityModelLayerRegistry.TexturedModelDataProvider) =
        EntityModelLayerRegistry.registerModelLayer(layer, provider)

    private fun make(id: String, layer: String): EntityModelLayer = EntityModelLayer(id(id), layer)
}