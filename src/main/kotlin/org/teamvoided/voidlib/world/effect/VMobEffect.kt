package org.teamvoided.voidlib.world.effect

import net.minecraft.core.particles.ParticleOptions
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory

@Suppress("unused")
class VMobEffect : MobEffect {
    constructor(category: MobEffectCategory, color: Int) : super(category, color)
    constructor(category: MobEffectCategory, color: Int, particle: ParticleOptions) : super(category, color, particle)
}