package org.teamvoided.voidcore.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.texture.PlayerSkin;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.voidcore.VoidCore;

@Mixin(PlayerListEntry.class)
public class PlayerSkinProviderMixin {
    @Shadow @Final private GameProfile profile;

    @ModifyReturnValue(method = "getSkin", at = @At("RETURN"))
    private PlayerSkin supports(PlayerSkin original) {
        return VoidCore.modifyPlayerCape(original, this.profile);
    }
}
