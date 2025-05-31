package org.teamvoided.headless

import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.headless.skull.SkullInit

@Suppress("unused")
object Headless {
    const val MODID = "headless"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(Headless::class.simpleName)

    fun init() {
        SkullInit.init()
    }
    fun id(path: String) = Identifier.of(MODID, path)
}
