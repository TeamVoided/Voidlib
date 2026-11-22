@file:Suppress("unused")

package org.teamvoided.voidlib.testmod.init.events

import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.animal.Cow
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.SmallFireball
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.phys.Vec3

fun postUse(
    result: InteractionResultHolder<ItemStack>, stack: ItemStack, level: Level, player: Player, hand: InteractionHand,
): InteractionResultHolder<ItemStack> {
    if (result.result != InteractionResult.PASS) return InteractionResultHolder.pass(stack)
    if (!stack.`is`(Items.FIRE_CHARGE)) return InteractionResultHolder.pass(stack)

    level.playSound(
        null,
        player.x, player.y, player.z,
        SoundEvents.FIRECHARGE_USE, SoundSource.NEUTRAL,
        0.5f, 0.4f / (level.getRandom().nextFloat() * 0.4f + 0.8f)
    )
    if (level is ServerLevel) {
        val fireball = SmallFireball(level, player, Vec3(1.0, 1.0,1.0))
        fireball.item = stack
        fireball.shootFromRotation(player, player.xRot, player.yRot, 0.0f, 1.5f, 1.0f)
        level.addFreshEntity(fireball)
    }
    player.awardStat(Stats.ITEM_USED.get(stack.item))
    stack.consume(1, player)
    return InteractionResultHolder.success(stack)
}

fun postUseOnBlock(result: InteractionResult, ctx: UseOnContext): InteractionResult {
    if (result != InteractionResult.PASS) return InteractionResult.PASS
    val stack = ctx.itemInHand
    if (!stack.`is`(Items.FLINT)) return InteractionResult.PASS

    val pos = ctx.clickedPos
    val face = ctx.clickedFace
    val level = ctx.level
    Block.popResourceFromFace(level, pos, face, ItemStack(Items.COBBLESTONE))

    val playPos = pos.relative(face)

    level.playSound(
        null,
        playPos.x + 0.5, playPos.y + 0.5, playPos.z + 0.5,
        SoundEvents.ANCIENT_DEBRIS_BREAK, SoundSource.NEUTRAL,
        0.5f, 0.4f / (level.getRandom().nextFloat() * 0.4f + 0.8f)
    )
    if (!level.isClientSide) if (level.random.nextInt(0, 5) == 0) {
        stack.consume(1, ctx.player)
        level.playSound(
            null,
            playPos.x + 0.5, playPos.y + 0.5, playPos.z + 0.5,
            SoundEvents.ITEM_BREAK, SoundSource.NEUTRAL,
            0.5f, 0.4f
        )
    }


    return InteractionResult.SUCCESS
}

fun postUseOnEntity(
    result: InteractionResult, player: Player, target: LivingEntity, hand: InteractionHand,
): InteractionResult {
    if (result != InteractionResult.PASS) return InteractionResult.PASS
    val stack = player.getItemInHand(hand)
    if (!stack.`is`(Items.NETHER_STAR)) return InteractionResult.PASS

    if (target !is Cow) return InteractionResult.PASS
    val level = player.level()

    level.playSound(
        null,
        player.x, player.y, player.z,
        SoundEvents.ILLUSIONER_CAST_SPELL, SoundSource.NEUTRAL,
        0.5f, 0.4f / (level.getRandom().nextFloat() * 0.4f + 0.8f)
    )
    if (level is ServerLevel) {
        target.convertTo(EntityType.MOOSHROOM, true)
    }
    player.awardStat(Stats.ITEM_USED.get(stack.item))
    stack.consume(1, player)

    return InteractionResult.SUCCESS

}

fun postFinishUsing(resultStack: ItemStack, usedStack: ItemStack, level: Level, player: LivingEntity): ItemStack {
    if (!usedStack.`is`(Items.DRIED_KELP)) return resultStack

    player.addEffect(MobEffectInstance(MobEffects.BLINDNESS, 10, 10))

    if (resultStack.`is`(Items.DRIED_KELP)) return resultStack

    return ItemStack(Items.BONE_MEAL)
}

fun postReleaseUsing(usedStack: ItemStack, level: Level, user: LivingEntity, remainingTime: Int) {
    if (!usedStack.`is`(Items.BOW)) return
    if (user is Player) {
        user.cooldowns.addCooldown(usedStack.item, Items.BOW.getUseDuration(usedStack, user) - remainingTime)
    }
}
