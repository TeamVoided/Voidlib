package org.teamvoided.voidlib

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import net.minecraft.resources.ResourceLocation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.voidlib.config.VoidlibConfig
import org.teamvoided.voidlib.init.VEvents
import org.teamvoided.voidlib.init.VLootContextParamSets
import org.teamvoided.voidlib.init.VLootContextParams

object Voidlib {

    const val MODID = "voidlib"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(Voidlib::class.simpleName)

    @JvmField
    var config = ConfigApi.registerAndLoadConfig(::VoidlibConfig)

    fun init() {
        log.info("Voids your license")
        VEvents.init()
        VLootContextParams.init()
        VLootContextParamSets.init()
    }

    fun id(namespace: String, path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(namespace, path)
    fun mc(path: String): ResourceLocation = ResourceLocation.withDefaultNamespace(path)
    fun id(path: String): ResourceLocation = id(MODID, path)

}