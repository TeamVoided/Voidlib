package org.teamvoided.voidlib.mixin.client.api.tooltip.context;

import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.teamvoided.voidlib.client.api.item.VoidlibTooltipContext;
import org.teamvoided.voidlib.client.impl.item.VoidlibTooltipContextImpl;

@Mixin(targets = {
        "net.minecraft.world.item.Item$TooltipContext$1",
        "net.minecraft.world.item.Item$TooltipContext$2",
        "net.minecraft.world.item.Item$TooltipContext$3"
})
public abstract class TooltipContextImplMixin implements VoidlibTooltipContextImpl, VoidlibTooltipContext {

    @Unique
    private @Nullable Screen vanillium$currentScreen = null;

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Override
    public @Nullable Screen getCurrentScreen() {
        return vanillium$currentScreen;
    }

    @Override
    public void voidlib_setCurrentScreen(@Nullable Screen screen) {
        vanillium$currentScreen = screen;
    }

}