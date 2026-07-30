package org.teamvoided.voidlib.data.registry

import net.minecraft.core.registries.Registries
import org.teamvoided.voidlib.Voidlib
import org.teamvoided.voidlib.util.key

object VPaintings {

    val QUESTIONABLE = create("questionable")

    fun create(id: String) = Registries.PAINTING_VARIANT.key(Voidlib.id(id))

}