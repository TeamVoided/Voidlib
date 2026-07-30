package org.teamvoided.voidlib.datagen.data.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class ItemTagsProvider(o: FabricDataOutput, p: CompletableFuture<HolderLookup.Provider>, blockTag: BlockTagProvider) :
    ItemTagProvider(o, p, blockTag) {

    override fun addTags(lookup: HolderLookup.Provider) {
//        copy(VBlockTags.BLOCK_TAG, VItemTags.ITEM_TAG)
//        copy(ConventionalBlockTags.STORAGE_BLOCKS, ConventionalItemTags.STORAGE_BLOCKS)

//        valueLookupBuilder(ItemTags.SWORDS)
//        valueLookupBuilder(VItemTags.ITEM_TAG)
//        valueLookupBuilder(ConventionalItemTags.HIDDEN_FROM_RECIPE_VIEWERS)
    }

}