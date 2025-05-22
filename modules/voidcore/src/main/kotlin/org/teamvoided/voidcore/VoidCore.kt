package org.teamvoided.voidcore

import com.mojang.authlib.GameProfile
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.MinecraftClient
import net.minecraft.client.texture.PlayerSkin
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import org.teamvoided.voidcore.CapeManager.getCustomCape

@Suppress("unused")
object VoidCore {
    val log = LoggerFactory.getLogger(this::class.simpleName)
    val MOID = "voidcore"
    val TEST_CAPE = Identifier.of(MOID, "textures/entity/cape/cape.png")

    fun init() {
        log.info("VoidCore is initializing")
    }

    fun initClient() {
        log.info("VoidCore is initializing client")

        ClientCommandRegistrationCallback.EVENT.register { dispatcher, ctx ->
            val root = literal("dump_skin")
                .executes {
                    val player = it.source.player
                    val skin = MinecraftClient.getInstance().skinProvider.getSkin(player.gameProfile)
                    player.sendSystemMessage(Text.literal("$skin"))

                    0
                }
                .build()
            dispatcher.root.addChild(root)
        }
        HudRenderCallback.EVENT.register { gui, delta ->
            gui.drawTexture(TEST_CAPE, 10, 10, 0f, 0f, 64, 32, 64, 32)
        }
    }

    @JvmStatic
    fun modifyPlayerCape(inputSkin: PlayerSkin, profile: GameProfile): PlayerSkin {
        var skin = inputSkin
        val customCape = profile.getCustomCape()
        if (customCape != null) skin = PlayerSkin(
            inputSkin.texture,
            inputSkin.textureUrl,
            customCape,
            inputSkin.capeTexture,
            inputSkin.model,
            inputSkin.secure
        )

        return skin
    }
}