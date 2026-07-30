package org.teamvoided.voidlib.data.tags

import net.minecraft.core.registries.Registries
import org.teamvoided.voidlib.Voidlib.id
import org.teamvoided.voidlib.util.tag

object VMenuTags {

    @JvmField
    val FORCE_SYNC_AFTER_CLOSE = create("force_sync_after_close")

    fun create(id: String) = Registries.MENU.tag(id(id))

}