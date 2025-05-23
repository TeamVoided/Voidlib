package org.teamvoided.vtags

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import org.teamvoided.vtags.gen.VBlockTagProvider
import org.teamvoided.vtags.gen.VItemTagProvider

@Suppress("unused")
object VTagsData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        val pack = gen.createPack()
        val blockTags = pack.addProvider(::VBlockTagProvider)
        pack.addProvider { o, r -> VItemTagProvider(o, r, blockTags) }
    }
}