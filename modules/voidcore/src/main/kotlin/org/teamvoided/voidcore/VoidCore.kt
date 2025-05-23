package org.teamvoided.voidcore

import org.slf4j.LoggerFactory

@Suppress("unused")
object VoidCore {
    val log = LoggerFactory.getLogger(this::class.simpleName)
    val MOID = "voidcore"

    fun init() {
        log.info("VoidCore is Starting")
    }

    fun initClient() {}
}