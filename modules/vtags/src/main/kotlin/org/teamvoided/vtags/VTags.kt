package org.teamvoided.vtags

import org.slf4j.LoggerFactory
import org.teamvoided.voidcore.helpers.createId

@Suppress("unused")
object VTags {
    val log = LoggerFactory.getLogger(this::class.simpleName)
    val MOID = "vtags"

    fun id(path: String) = createId(MOID, path)
}