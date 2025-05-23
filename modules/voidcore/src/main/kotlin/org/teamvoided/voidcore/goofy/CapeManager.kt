package org.teamvoided.voidcore.goofy

import com.mojang.authlib.GameProfile
import net.minecraft.client.texture.PlayerSkin
import net.minecraft.util.Identifier
import java.net.URI
import java.util.*

@Suppress("unused")
object CapeManager {
    val mainURl = "https://teamvoided-endpoints.fly.dev/v1"

    private val capeOwners = mutableMapOf<UUID, Identifier>()
    private val noCape = mutableListOf<UUID>()

    fun GameProfile.getCustomCape(): Identifier? {
        val id = this.id
        val cape = capeOwners[id]
        if (cape != null) return cape

        if (noCape.contains(id)) return null

        return fetchCape(id)
    }

    //private fun getCapeIdentifier(id: UUID): Identifier {}
    private fun fetchCape(id: UUID): Identifier? {
        val isMember = isMember(id)
        if (isMember) {
            // getCapeIdentifier(id)
        } else noCape.add(id)
        return null
    }

    fun isMember(uuid: UUID): Boolean {
        val text = URI.create("$mainURl/is_member/$uuid").toURL().readText()
        return text == "true"
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