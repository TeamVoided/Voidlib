package org.teamvoided.voidlib.mixin.client.api.tooltip.context;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.voidlib.client.impl.item.VoidlibTooltipContextImpl;

@Mixin(CreativeModeInventoryScreen.class)
public class CreativeModeInventoryScreenMixin extends Screen {

    protected CreativeModeInventoryScreenMixin(Component component) {
        super(component);
    }

    @ModifyExpressionValue(method = "getTooltipFromContainerItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$TooltipContext;of(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/item/Item$TooltipContext;"))
    Item.TooltipContext addExtraContext(Item.TooltipContext original) {
        ((VoidlibTooltipContextImpl) original).voidlib_setCurrentScreen(this);
        return original;
    }
}
