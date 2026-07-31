package org.teamvoided.voidlib.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.voidlib.data.tags.VBlockTags;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {

    @ModifyExpressionValue(
            method = "getDestroyProgress",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getDestroySpeed(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)F")
    )
    private static float run(float original, BlockState state, Player player, BlockGetter level, BlockPos pos) {
        return (level.getBlockState(pos).is(VBlockTags.INSTAMINE_WITH_TOOL) && player.hasCorrectToolForDrops(state)) ? 0f : original;
    }

}
