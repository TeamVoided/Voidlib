package org.teamvoided.voidmill.sign

import net.minecraft.block.WoodType
import net.minecraft.block.sign.CeilingHangingSignBlock
import net.minecraft.util.Identifier

@Suppress("unused")
class VoidCeilingHangingSignBlock(override val texture: Identifier, woodType: WoodType, settings: Settings) :
    CeilingHangingSignBlock( woodType, settings.solid()), VoidSign

