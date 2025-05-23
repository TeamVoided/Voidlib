package org.teamvoided.vtags.v1

import net.minecraft.registry.RegistryKeys
import org.teamvoided.voidcore.helpers.tag
import org.teamvoided.vtags.VTags.id


@Suppress("unused")
object VItemTags {
    // Ores
    val DEEPSLATE_ORES = create("deepslate_ores")
    val STONE_ORES = create("stone_ores")
    val NETHERRACK_ORES = create("netherrack_ores")
    val RAW_ORE_BLOCKS = create("raw_ore_blocks")
    
    fun create(name: String) = RegistryKeys.ITEM.tag(id(name))
}