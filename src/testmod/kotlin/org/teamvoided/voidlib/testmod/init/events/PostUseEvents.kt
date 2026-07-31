@file:Suppress("unused")

package org.teamvoided.voidlib.testmod.init.events

import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.Registries
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.animal.cow.Cow
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.Projectile
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.SnowballItem
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block

fun postUse(
    result: InteractionResult, stack: ItemStack, level: Level, player: Player, hand: InteractionHand,
): InteractionResult {
    if (result != InteractionResult.PASS) return InteractionResult.PASS
    if (!stack.`is`(Items.FIRE_CHARGE)) return InteractionResult.PASS

    level.playSound(
        null,
        player.x, player.y, player.z,
        SoundEvents.FIRECHARGE_USE, SoundSource.NEUTRAL,
        0.5f, 0.4f / (level.getRandom().nextFloat() * 0.4f + 0.8f)
    )
    if (level is ServerLevel) Projectile.spawnProjectileFromRotation(
        { server, living, stack ->
            val smallFireball = SmallFireball(level, living.x, living.eyePosition.y, living.z, living.lookAngle)
            smallFireball.item = stack
            smallFireball
        }, level, stack, player, 0.0f, SnowballItem.PROJECTILE_SHOOT_POWER, 1.0f
    )
    player.awardStat(Stats.ITEM_USED.get(stack.item))
    stack.consume(1, player)
    return InteractionResult.SUCCESS
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
    if (level.random.nextInt(0, 5) == 0) {
        stack.consume(1, ctx.player)
        level.playSound(
            null,
            playPos.x + 0.5, playPos.y + 0.5, playPos.z + 0.5,
            stack[DataComponents.BREAK_SOUND]!!.value(), SoundSource.NEUTRAL,
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
        target.variant = level.registryAccess().lookupOrThrow(Registries.COW_VARIANT).getRandom(level.random).get()
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
        user.cooldowns.addCooldown(usedStack, Items.BOW.getUseDuration(usedStack, user) - remainingTime)
    }
}
