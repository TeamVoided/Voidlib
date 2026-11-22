package org.teamvoided.voidlib.data.tags

import net.minecraft.core.registries.Registries
import org.teamvoided.voidlib.Voidlib.id
import org.teamvoided.voidlib.util.tag

object VBlockTags {

    /**
     * Any block in this tag will be instantly breakable in if the correct tool at the correct level is used.
     */
    @JvmField
    val INSTAMINE_WITH_TOOL = create("instamine_with_tool")

    fun create(id: String) = Registries.BLOCK.tag(id(id))

}