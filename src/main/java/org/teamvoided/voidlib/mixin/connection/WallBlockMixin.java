package org.teamvoided.voidlib.mixin.connection;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.voidlib.api.block.BlockConnection;

@Mixin(WallBlock.class)
public abstract class WallBlockMixin extends Block {

    protected WallBlockMixin(Properties settings) {
        super(settings);
    }

    @ModifyReturnValue(method = "connectsTo", at = @At("RETURN"))
    private boolean blockConnectionCheck(boolean original, BlockState state, @Local(argsOnly = true) Direction dir) {
        return original || (state.getBlock() instanceof BlockConnection connection && connection.allowWallsToConnect(state, dir));
    }

}