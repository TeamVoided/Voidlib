package org.teamvoided.voidlib.testmod

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.resources.ResourceLocation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.voidlib.Voidlib
import org.teamvoided.voidlib.client.util.getCurrentScreen
import org.teamvoided.voidlib.client.util.getMenu
import org.teamvoided.voidlib.testmod.init.VTEvents
import org.teamvoided.voidlib.world.inventory.getTypeHolder

object VTest : ModInitializer {

    const val MODID = "voidlib_testmod"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(MODID)

    override fun onInitialize() {
        log.info("Test modding: $MODID!")
        VTEvents.init()

        ItemTooltipCallback.EVENT.register { _, _, flags, tooltips ->
            val screen = getCurrentScreen()
            if (flags.isAdvanced && screen != null) {
                tooltips.addAll(
                    buildList {
                        add(text("Screen: ${screen.javaClass.simpleName}"))
                        getMenu(screen)?.let {
                            add(text("Menu: ${it.javaClass.simpleName}"))
                            getTypeHolder(it)?.let { holder -> add(text("Menu Type: $holder")) }
                        }
                    }
                )
            }
        }
    }

    private fun text(message: String): MutableComponent? =
        Component.literal(message).withStyle(ChatFormatting.DARK_GRAY)

    fun id(path: String): ResourceLocation = Voidlib.id(MODID, path)

}