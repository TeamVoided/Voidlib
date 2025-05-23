package org.teamvoided.voidcore

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import org.slf4j.LoggerFactory
import java.util.*

@Suppress("unused")
object VoidCore {
    val log = LoggerFactory.getLogger(this::class.simpleName)
    val MOID = "voidcore"

    val IS_ENDER = listOf("022e8cd8-4ecf-4416-b0e8-3bfba11498f0", "a5fc6689-7d19-4c39-a04e-95e4ec460298")
        .map(UUID::fromString)

    fun init() {
        log.info("VoidCore is initializing")

        ServerPlayConnectionEvents.JOIN.register(::hello)
    }

    fun hello(handler: ServerPlayNetworkHandler, sender: PacketSender, server: MinecraftServer) {
        if (IS_ENDER.contains(handler.player.gameProfile.id)) {
            for (player in server.playerManager.playerList) {
                player.world.playSoundFromEntity(
                    null, player,
                    SoundEvents.ENTITY_WITHER_SPAWN, SoundCategory.MASTER,
                    1f, 1f
                )
            }
        }
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
    }
}