package org.teamvoided.vtags.gen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider
import net.minecraft.registry.HolderLookup
import org.teamvoided.vtags.v1.VBlockTags
import org.teamvoided.vtags.v1.VItemTags
import java.util.concurrent.CompletableFuture

class VItemTagProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>, blockTags: BlockTagProvider) :
    ItemTagProvider(o, r, blockTags) {
    override fun configure(arg: HolderLookup.Provider) {
        copyTags()
    }

    fun copyTags() {
        copy(VBlockTags.DEEPSLATE_ORES, VItemTags.DEEPSLATE_ORES)
        copy(VBlockTags.STONE_ORES, VItemTags.STONE_ORES)
        copy(VBlockTags.NETHERRACK_ORES, VItemTags.NETHERRACK_ORES)
        copy(VBlockTags.RAW_ORE_BLOCKS, VItemTags.RAW_ORE_BLOCKS)
    }
}
