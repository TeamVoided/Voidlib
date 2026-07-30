package org.teamvoided.voidlib.mixin.client.api.tooltip.context;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.voidlib.client.impl.item.VoidlibTooltipContextImpl;

@Mixin(Screen.class)
public class ScreenMixin {
    @ModifyExpressionValue(method = "getTooltipFromItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$TooltipContext;of(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/item/Item$TooltipContext;"))
   private static Item.TooltipContext addExtraContext(Item.TooltipContext original) {
        ((VoidlibTooltipContextImpl) original).voidlib_setCurrentScreen(Minecraft.getInstance().screen);
        return original;
    }
}
