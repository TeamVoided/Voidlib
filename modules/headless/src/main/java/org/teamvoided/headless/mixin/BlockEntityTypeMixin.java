package org.teamvoided.headless.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.headless.block.Headless;

@Mixin(BlockEntityType.class)
public abstract class BlockEntityTypeMixin {

    @ModifyReturnValue(method = "supports", at = @At("RETURN"))
    private boolean supports(boolean original, BlockState state) {
        var block = state.getBlock();
        return original || (block instanceof Headless && block instanceof AbstractSkullBlock);
    }
}
