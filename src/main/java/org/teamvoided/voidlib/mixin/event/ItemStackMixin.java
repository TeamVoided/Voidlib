package org.teamvoided.voidlib.mixin.event;


import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teamvoided.voidlib.api.event.InventoryItemEvents;
import org.teamvoided.voidlib.api.event.PostUseItemEvents;
import org.teamvoided.voidlib.impl.PrimaryModifierRegistryImpl;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow
    public abstract ItemStack copy();

    @ModifyReturnValue(method = "use", at = @At("RETURN"))
    private InteractionResultHolder<ItemStack> postUseHook(InteractionResultHolder<ItemStack> original, Level level, Player player, InteractionHand hand) {
        return PostUseItemEvents.POST_USE.invoker().interact(original, (ItemStack) (Object) this, level, player, hand);
    }

    @ModifyReturnValue(method = "useOn", at = @At("RETURN"))
    private InteractionResult postUseOnBlockHook(InteractionResult original, UseOnContext context) {
        return PostUseItemEvents.POST_USE_ON_BLOCK.invoker().interact(original, context);
    }

    @ModifyReturnValue(method = "interactLivingEntity", at = @At("RETURN"))
    private InteractionResult postUseOnEntityHook(InteractionResult original, Player user, LivingEntity entity, InteractionHand hand) {
        return PostUseItemEvents.POST_USE_ON_ENTITY.invoker().interact(original, user, entity, hand);
    }

    @Inject(method = "finishUsingItem", at = @At("HEAD"))
    private void finishUsingItemCopy(Level level, LivingEntity livingEntity, CallbackInfoReturnable<ItemStack> cir, @Share("stackRefForPostHook") LocalRef<ItemStack> stackLocalRef) {
        stackLocalRef.set(copy());
    }

    @ModifyReturnValue(method = "finishUsingItem", at = @At("RETURN"))
    private ItemStack postFinishUsingHook(ItemStack original, Level level, LivingEntity user, @Share("stackRefForPostHook") LocalRef<ItemStack> stackLocalRef) {
        return PostUseItemEvents.POST_FINISH_USING.invoker().interact(original, stackLocalRef.get(), level, user);
    }

    @Inject(method = "releaseUsing", at = @At("RETURN"))
    private void postOnStoppedUsingHook(Level level, LivingEntity user, int remainingTime, CallbackInfo ci) {
        PostUseItemEvents.POST_RELEASE_USING.invoker().interact((ItemStack) (Object) this, level, user, remainingTime);
    }

    @WrapOperation(method = "overrideStackedOnOther", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;overrideStackedOnOther(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/inventory/Slot;Lnet/minecraft/world/inventory/ClickAction;Lnet/minecraft/world/entity/player/Player;)Z"))
    boolean onClickedOnOtherHook(Item instance, ItemStack thisStack, Slot otherSlot, ClickAction clickType, Player player, Operation<Boolean> original) {
        var bool = InventoryItemEvents.ON_CLICKED_ON_OTHER.invoker().interact(thisStack, otherSlot, clickType, player);
        if (bool != TriState.DEFAULT) return bool.get();
        return original.call(instance, thisStack, otherSlot, clickType, player);
    }

    @WrapOperation(method = "overrideOtherStackedOnMe", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;overrideOtherStackedOnMe(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/inventory/Slot;Lnet/minecraft/world/inventory/ClickAction;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/SlotAccess;)Z"))
    boolean onClickedOnOtherHook(Item instance, ItemStack thisStack, ItemStack otherStack, Slot thisSlot, ClickAction clickType, Player player, SlotAccess cursorStackReference, Operation<Boolean> original) {
        var bool = InventoryItemEvents.ON_CLICKED.invoker().interact(thisStack, otherStack, thisSlot, clickType, player, cursorStackReference);
        if (bool != TriState.DEFAULT) return bool.get();
        return original.call(instance, thisStack, otherStack, thisSlot, clickType, player, cursorStackReference);
    }

    @Inject(method = "addModifierTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;operation()Lnet/minecraft/world/entity/ai/attributes/AttributeModifier$Operation;", ordinal = 0))
    void modifyTooltipContent(Consumer<Component> textConsumer, @Nullable Player player, Holder<Attribute> attribute, AttributeModifier modifier, CallbackInfo ci,
                              @Local(ordinal = 0) LocalDoubleRef amount, @Local(ordinal = 0) LocalBooleanRef isPrimary) {
        PrimaryModifierRegistryImpl.tryApply(player, modifier, attribute, amount, isPrimary);
    }

}
