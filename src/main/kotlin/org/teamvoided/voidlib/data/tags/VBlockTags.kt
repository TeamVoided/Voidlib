package org.teamvoided.voidlib.data.tags

import net.minecraft.core.registries.Registries
import org.teamvoided.voidlib.Voidlib.id
import org.teamvoided.voidlib.util.tag

object VBlockTags {

    @JvmField
    val INSTAMINE_WITH_TOOL = create("instamine_with_tool")

    fun create(id: String) = Registries.BLOCK.tag(id(id))

}