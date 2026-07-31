package org.teamvoided.voidlib.datagen.data.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider
import net.minecraft.core.HolderLookup
import org.teamvoided.voidlib.data.tags.VBlockTags
import java.util.concurrent.CompletableFuture

class BlockTagsProvider(o: FabricDataOutput, p: CompletableFuture<HolderLookup.Provider>) : BlockTagProvider(o, p) {

    override fun addTags(lookup: HolderLookup.Provider) {
//        valueLookupBuilder(BlockTags.LOGS)
        valueLookupBuilder(VBlockTags.INSTAMINE_WITH_TOOL)
//        valueLookupBuilder(ConventionalBlockTags.STORAGE_BLOCKS)
    }

}