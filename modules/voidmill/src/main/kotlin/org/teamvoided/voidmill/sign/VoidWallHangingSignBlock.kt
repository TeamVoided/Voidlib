package org.teamvoided.voidmill.sign

import net.minecraft.block.WoodType
import net.minecraft.block.sign.WallHangingSignBlock
import net.minecraft.util.Identifier

@Suppress("unused")
class VoidWallHangingSignBlock(override val texture: Identifier, woodType: WoodType, settings: Settings) :
    WallHangingSignBlock(woodType, settings.solid()), VoidSign

