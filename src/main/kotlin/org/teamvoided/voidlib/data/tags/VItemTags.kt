package org.teamvoided.voidlib.data.tags

import net.minecraft.core.registries.Registries
import org.teamvoided.voidlib.Voidlib.id
import org.teamvoided.voidlib.util.tag

object VItemTags {

//    val ITEM_TAG = create("item_tag")

    fun create(id: String) = Registries.ITEM.tag(id(id))

}