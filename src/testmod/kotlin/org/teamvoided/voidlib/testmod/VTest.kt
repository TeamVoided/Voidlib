package org.teamvoided.voidlib.testmod

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.voidlib.client.util.getCurrentScreen
import org.teamvoided.voidlib.testmod.init.VEvents

object VTest : ModInitializer {

    const val MODID = "voidlib_testmod"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(MODID)

    override fun onInitialize() {
        log.info("Test modding: $MODID!")
        VEvents.init()

        ItemTooltipCallback.EVENT.register { stack, ctx, flags, tooltips ->
            val screen = getCurrentScreen()
            if (flags.isAdvanced && screen != null) tooltips.add(
                Component.literal("Screen: ${screen.javaClass.simpleName}").withStyle(ChatFormatting.DARK_GRAY)
            )
        }
    }

}