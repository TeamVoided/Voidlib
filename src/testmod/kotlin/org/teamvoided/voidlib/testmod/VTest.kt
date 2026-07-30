package org.teamvoided.voidlib.testmod

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object VTest : ModInitializer {

    const val MODID = "voidlib_testmod"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(MODID)

    override fun onInitialize() {
        log.info("Test modding: $MODID!")
    }

}