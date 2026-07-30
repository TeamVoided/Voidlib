package org.teamvoided.voidlib.datagen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderLookup.RegistryLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import org.teamvoided.voidlib.Voidlib
import org.teamvoided.voidlib.Voidlib.log
import org.teamvoided.voidlib.datagen.assets.EnLangProvider
import org.teamvoided.voidlib.datagen.assets.ModelProvider
import org.teamvoided.voidlib.datagen.data.registry.Paintings
import org.teamvoided.voidlib.datagen.data.tags.BlockTagsProvider
import org.teamvoided.voidlib.datagen.data.tags.ItemTagsProvider
import java.util.concurrent.CompletableFuture

object VoidlibData : DataGeneratorEntrypoint {

    override fun getEffectiveModId(): String = Voidlib.MODID

    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        val pack = gen.createPack()
        log.info("Running \"${gen.modContainer.metadata.name}\" Datagen!")

        // Assets
        pack.addProvider(::EnLangProvider)
        pack.addProvider(::ModelProvider)
        // Data
        pack.addProvider(::RegistryProvider)
        val blockTags = pack.addProvider(::BlockTagsProvider)
        pack.addProvider { o, f -> ItemTagsProvider(o, f, blockTags) }
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(Registries.PAINTING_VARIANT, Paintings::bootstrap)
    }

    class RegistryProvider(o: FabricDataOutput, p: CompletableFuture<HolderLookup.Provider>) :
        FabricDynamicRegistryProvider(o, p) {

        override fun getName(): String = "Registry Gen"

        override fun configure(provider: HolderLookup.Provider, entries: Entries) {
            entries.addAll(provider.lookupOrThrow(Registries.PAINTING_VARIANT))
        }

        fun <T : Any> Entries.addEverything(registry: RegistryLookup<T>): MutableList<Holder<T>> {
            return registry.listElementIds().map { add(registry, it) }.toList().toMutableList()
        }

    }
}