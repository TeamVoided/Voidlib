package org.teamvoided.voidcore

import com.mojang.authlib.GameProfile
import net.minecraft.client.texture.PlayerSkin

@Suppress("unused")
fun modifyPlayerCape(inputSkin: PlayerSkin, profile: GameProfile): PlayerSkin {
    var skin = inputSkin
    val customCape = null // profile.getCustomCape()
    @Suppress("SENSELESS_COMPARISON")
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