package org.teamvoided.voidlib

import net.minecraft.resources.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Voidlib {

    const val MODID = "voidlib"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(Voidlib::class.simpleName)

//    @JvmField
//    var config = ConfigApi.registerAndLoadConfig(::VoidlibConfig)

    fun init() {
        log.info("Voids your license")
    }

    fun id(namespace: String, path: String): Identifier = Identifier.fromNamespaceAndPath(namespace, path)
    fun mc(path: String): Identifier = Identifier.withDefaultNamespace(path)
    fun id(path: String) = id(MODID, path)

}