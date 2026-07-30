package org.teamvoided.voidlib.data.tags

import net.minecraft.core.registries.Registries
import org.teamvoided.voidlib.Voidlib.id
import org.teamvoided.voidlib.util.tag

object VBlockTags {

//    val BLOCK_TAG = create("block_tag")

    fun create(id: String) = Registries.BLOCK.tag(id(id))

}