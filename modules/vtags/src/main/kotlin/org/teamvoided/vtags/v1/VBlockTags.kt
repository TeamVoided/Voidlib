package org.teamvoided.vtags.v1

import net.minecraft.registry.RegistryKeys
import org.teamvoided.voidcore.helpers.mc.tag
import org.teamvoided.vtags.VTags.id


@Suppress("unused")
object VBlockTags {
    // Ores
    val DEEPSLATE_ORES = create("deepslate_ores")
    val STONE_ORES = create("stone_ores")
    val NETHERRACK_ORES = create("netherrack_ores")
    val RAW_ORE_BLOCKS = create("raw_ore_blocks")

    // Light Blocks
    val TORCHES = create("torches")
    val LANTERNS =  create("lanterns")

    fun create(name: String) = RegistryKeys.BLOCK.tag(id(name))
}