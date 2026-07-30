package org.teamvoided.voidlib.mixin.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.voidlib.api.inventory.slot.CustomSlotBackground;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin {

    @Inject(method = "renderSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z", ordinal = 5))
    void addSlotBackgrounds(GuiGraphics guiGraphics, Slot slot, int i, int j, CallbackInfo ci) {
        if (slot instanceof CustomSlotBackground bSlot) {
            var sprite = bSlot.getBackground();
            if (sprite != null) {
                guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, bSlot.getBackground(), slot.x - 1, slot.y - 1, 18, 18);
            }
        }
    }

}