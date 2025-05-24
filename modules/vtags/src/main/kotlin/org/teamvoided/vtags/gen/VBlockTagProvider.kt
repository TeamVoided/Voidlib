package org.teamvoided.vtags.gen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider
import net.minecraft.block.Blocks.*
import net.minecraft.registry.HolderLookup
import org.teamvoided.vtags.v1.VBlockTags
import java.util.concurrent.CompletableFuture

class VBlockTagProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : BlockTagProvider(o, r) {
    override fun configure(provider: HolderLookup.Provider) {
        getOrCreateTagBuilder(VBlockTags.STONE_ORES).add(
            COAL_ORE,
            COPPER_ORE,
            IRON_ORE,
            GOLD_ORE,
            REDSTONE_ORE,
            LAPIS_ORE,
            DIAMOND_ORE,
            EMERALD_ORE
        )
        getOrCreateTagBuilder(VBlockTags.DEEPSLATE_ORES).add(
            DEEPSLATE_COAL_ORE,
            DEEPSLATE_COPPER_ORE,
            DEEPSLATE_IRON_ORE,
            DEEPSLATE_GOLD_ORE,
            DEEPSLATE_REDSTONE_ORE,
            DEEPSLATE_LAPIS_ORE,
            DEEPSLATE_DIAMOND_ORE,
            DEEPSLATE_EMERALD_ORE
        )
        getOrCreateTagBuilder(VBlockTags.NETHERRACK_ORES)
            .add(NETHER_QUARTZ_ORE, NETHER_GOLD_ORE)
        getOrCreateTagBuilder(VBlockTags.RAW_ORE_BLOCKS).add(
            RAW_IRON_BLOCK,
            RAW_GOLD_BLOCK,
            RAW_COPPER_BLOCK
        )

        getOrCreateTagBuilder(VBlockTags.TORCHES).add(
            TORCH, WALL_TORCH,
            REDSTONE_TORCH, REDSTONE_WALL_TORCH,
            SOUL_TORCH, SOUL_WALL_TORCH
        )
        getOrCreateTagBuilder(VBlockTags.LANTERNS)
            .add(LANTERN, SOUL_LANTERN)
    }
}
