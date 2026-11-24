package org.teamvoided.voidlib.init
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet
import net.minecraft.world.level.storage.loot.parameters.LootContextParams
import org.teamvoided.voidlib.Voidlib.id
import org.teamvoided.voidlib.mixin.accessors.LootContextParamSetsAccessor
import java.util.function.UnaryOperator

object VLootContextParamSets {

    val BLOCK_INTERACT = register("block_interact") { builder ->
        builder
            .required(LootContextParams.BLOCK_STATE)
            .required(LootContextParams.ORIGIN)
            .optional(LootContextParams.BLOCK_ENTITY)
            .optional(VLootContextParams.INTERACTING_ENTITY)
            .optional(LootContextParams.TOOL)
    }

    fun init() = Unit

    fun register(name: String, build: UnaryOperator<LootContextParamSet.Builder>): LootContextParamSet {
        val paramSet = build.apply(LootContextParamSet.Builder()).build()
        val id = id(name)
        val oldSet = LootContextParamSetsAccessor.voidlib_REGISTRY().put(id, paramSet)
        check(oldSet == null) { "Loot table parameter set $id is already registered" }
        return paramSet
    }

}