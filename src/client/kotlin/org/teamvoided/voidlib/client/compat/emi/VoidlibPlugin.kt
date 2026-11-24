package org.teamvoided.voidlib.client.compat.emi

import dev.emi.emi.api.EmiRegistry
import dev.emi.emi.api.stack.EmiStack
import org.slf4j.Logger
import org.teamvoided.voidlib.Voidlib.MODID
import org.teamvoided.voidlib.Voidlib.log
import org.teamvoided.voidlib.impl.event.BlockStrippingRegistryIml

object VoidlibPlugin : VEmiPlugin {

    override fun log(): Logger = log

    override fun modid(): String = MODID

    override fun register(registry: EmiRegistry) {
        safely("world interaction") { addWorldInteraction(registry) }
    }

    fun addWorldInteraction(registry: EmiRegistry) {
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

        /* val shears = getShears()
         for (carved in BuiltInRegistries.ITEM.mapNotNull { if (it is BlockItem && it.block is CarvableBlock) it.block as CarvableBlock else null }) {
             val from = (carved as Block).asItem()
             val to = carved.getCarvedBlock().asItem()
             if (to == Items.AIR) {
                 continue
             }

             val input = EmiStack.of(from)
             val output = EmiStack.of(to)
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
         }*/
    }

}