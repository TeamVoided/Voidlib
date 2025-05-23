package org.teamvoided.voidcore.goofy

import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import org.teamvoided.voidcore.IS_ENDER

@Suppress("unused")
object EnderJoinSound {
    fun init() {
        ServerPlayConnectionEvents.JOIN.register(::playJoinSound)
    }

    fun playJoinSound(handler: ServerPlayNetworkHandler, sender: PacketSender, server: MinecraftServer) {
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
}