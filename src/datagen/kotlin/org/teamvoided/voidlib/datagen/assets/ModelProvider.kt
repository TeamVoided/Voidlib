package org.teamvoided.voidlib.datagen.assets


import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.model.ModelTemplates
import net.minecraft.world.item.Item


class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    override fun generateBlockStateModels(gen: BlockModelGenerators) {
//        gen.createTrivialCube(TemplateBlocks.APPLE_BLOCK)
    }

    val single = listOf<Item>()

    override fun generateItemModels(gen: ItemModelGenerators) {
        single.forEach { gen.generateFlatItem(it, ModelTemplates.FLAT_ITEM) }
    }

}