package org.teamvoided.voidlib.api.events

import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory.createArrayBacked
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level

object PostUseItemEvents {

    /**
     * Callback that runs when {@link ItemStack#use(Level, Player, InteractionHand)} returns.
     */
    @JvmField
    val POST_USE: Event<PostUseItemCallback> = createArrayBacked(PostUseItemCallback::class.java) { listeners ->
        PostUseItemCallback { result, stack, level, player, hand ->
            listeners.forEach { it.interact(result, stack, level, player, hand) }
            result
        }
    }

    /**
     * Callback that runs when {@link ItemStack#useOn(UseOnContext)} returns.
     */
    @JvmField
    val POST_USE_ON_BLOCK: Event<PostUseOnBlockItemCallback> =
        createArrayBacked(PostUseOnBlockItemCallback::class.java) { listeners ->
            PostUseOnBlockItemCallback { originalResult, context ->
                for (callback in listeners) {
                    var result = callback.interact(originalResult, context)
                    if (result != InteractionResult.PASS) {
                        return@PostUseOnBlockItemCallback result
                    }
                }
                originalResult
            }
        }


    /**
     * Callback that runs when {@link ItemStack#interactLivingEntity(Player, LivingEntity, InteractionHand)} returns.
     */
    @JvmField
    val POST_USE_ON_ENTITY: Event<PostUseOnEntityItemCallback> =
        createArrayBacked(PostUseOnEntityItemCallback::class.java) { listeners ->
            PostUseOnEntityItemCallback { originalResult, player, target, hand ->
                for (callback in listeners) {
                    var result = callback.interact(originalResult, player, target, hand)
                    if (result != InteractionResult.PASS) {
                        return@PostUseOnEntityItemCallback result
                    }
                }
                originalResult
            }
        }

    /**
     * Callback that runs when {@link ItemStack#finishUsingItem(Level, LivingEntity)} returns.
     */
    @JvmField
    val POST_FINISH_USING: Event<PostUsingItemCallback> =
        createArrayBacked(PostUsingItemCallback::class.java) { listeners ->
            PostUsingItemCallback { originalResultStack, usedStack, level, player ->
                var modifiedStack = originalResultStack
                for (callback in listeners) {
                    modifiedStack = callback.interact(modifiedStack, usedStack, level, player)
                }
                modifiedStack
            }
        }

    /**
     * Callback that runs when {@link ItemStack#releaseUsing(Level, LivingEntity, int)} returns.
     */
    @JvmField
    val POST_RELEASE_USING: Event<PostStopUsingItemCallback> =
        createArrayBacked(PostStopUsingItemCallback::class.java) { listeners ->
            PostStopUsingItemCallback { stack, level, user, remainingTime ->
                listeners.forEach { it.interact(stack, level, user, remainingTime) }
            }
        }

    @FunctionalInterface
    fun interface PostUseItemCallback {
        fun interact(
            result: InteractionResult, stack: ItemStack, level: Level, player: Player, hand: InteractionHand,
        ): InteractionResult
    }

    @FunctionalInterface
    fun interface PostUseOnBlockItemCallback {
        fun interact(result: InteractionResult, context: UseOnContext): InteractionResult
    }

    @FunctionalInterface
    fun interface PostUseOnEntityItemCallback {
        fun interact(
            result: InteractionResult, player: Player, target: LivingEntity, hand: InteractionHand,
        ): InteractionResult
    }

    @FunctionalInterface
    fun interface PostUsingItemCallback {
        fun interact(resultStack: ItemStack, usedStack: ItemStack, level: Level, player: LivingEntity): ItemStack
    }

    @FunctionalInterface
    fun interface PostStopUsingItemCallback {
        fun interact(usedStack: ItemStack, level: Level, user: LivingEntity, remainingTime: Int)
    }
}

