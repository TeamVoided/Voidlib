package org.teamvoided.voidlib.client.compat.emi

import dev.emi.emi.api.EmiPlugin
import dev.emi.emi.api.EmiRegistry
import dev.emi.emi.api.recipe.EmiRecipe
import net.minecraft.resources.ResourceLocation
import org.slf4j.Logger
import org.teamvoided.voidlib.Voidlib.id
import java.util.function.Function

interface VEmiPlugin : EmiPlugin {

    fun log(): Logger

    fun modid(): String

    fun synthetic(type: String, name: String): ResourceLocation = id(modid(), "/$type/$name")

    fun safely(name: String, runnable: Runnable) {
        try {
            runnable.run()
        } catch (e: Throwable) {
            log().warn("Exception thrown when reloading '$name' step in '${modid()}' EMI plugin", e)
        }
    }

    fun EmiRegistry.addRecipeSafe(id: ResourceLocation, supplier: Function<ResourceLocation, EmiRecipe>) {
        try {
            addRecipe(supplier.apply(id))
        } catch (e: Throwable) {
            log().warn("Exception thrown when parsing EMI recipe ($id)", e)
        }
    }

}