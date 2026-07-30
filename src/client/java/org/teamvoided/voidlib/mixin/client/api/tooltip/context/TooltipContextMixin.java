package org.teamvoided.voidlib.mixin.client.api.tooltip.context;

import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.teamvoided.voidlib.client.api.item.VoidlibTooltipContext;

@Mixin(Item.TooltipContext.class)
public interface TooltipContextMixin extends VoidlibTooltipContext {
}