package org.teamvoided.voidlib.client.compat.emi

import dev.emi.emi.api.EmiRegistry
import dev.emi.emi.api.recipe.EmiWorldInteractionRecipe
import dev.emi.emi.api.stack.EmiStack
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.BlockItem
import net.minecraft.world.level.block.Block
import org.slf4j.Logger
import org.teamvoided.voidlib.Voidlib.MODID
import org.teamvoided.voidlib.Voidlib.log
import org.teamvoided.voidlib.api.block.Carvable
import org.teamvoided.voidlib.impl.event.BlockStrippingRegistryIml

object VoidlibPlugin : VEmiPlugin {

    override fun log(): Logger = log

    override fun modid(): String = MODID

    override fun register(registry: EmiRegistry) {
        safely("world interaction") { addWorldInteraction(registry) }
    }

    fun addWorldInteraction(registry: EmiRegistry) {
        addBlockStripping(registry)
        addCarving(registry)
    }

    fun addBlockStripping(registry: EmiRegistry) {
        val axes = getAxes()
        for ((from, to) in BlockStrippingRegistryIml.BLOCK_STATE_MAP) {
            val input = EmiStack.of(from)
            val output = EmiStack.of(to)
            if (isHiddenOrEmpty(input) || isHiddenOrEmpty(output)) {
                continue
            }
            registry.addRecipeSafe(synthetic("world/stripping", subId(from))) { id ->
                basicWorld(input, axes, output, id)
            }
        }
    }

    fun addCarving(registry: EmiRegistry) {
        val shears = getShears()
        for (carved in BuiltInRegistries.ITEM.mapNotNull { (it as? BlockItem)?.block as? Carvable }) {
            val from = (carved as? Block)?.asItem() ?: continue
            val input = EmiStack.of(from)
            val output = EmiStack.of(carved.getCarvedBlock())
            if (isHiddenOrEmpty(input) || isHiddenOrEmpty(output)) {
                continue
            }
            registry.addRecipeSafe(synthetic("world/carving", subId(from))) { id ->
                EmiWorldInteractionRecipe.builder()
                    .id(id)
                    .leftInput(input)
                    .rightInput(shears, true)
                    .output(output)
                    // TODO add seed loot tables (needs loot table fetching)
                    .build()
            }
        }
    }

}