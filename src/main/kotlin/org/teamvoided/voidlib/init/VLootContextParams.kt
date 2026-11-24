package org.teamvoided.voidlib.init

import net.minecraft.world.entity.Entity
import net.minecraft.world.level.storage.loot.parameters.LootContextParam
import org.teamvoided.voidlib.Voidlib.id

object VLootContextParams {

    val INTERACTING_ENTITY = contextParam<Entity>("interacting_entity")

    fun init() = Unit

    fun <T> contextParam(id: String): LootContextParam<T> = LootContextParam(id(id))

}