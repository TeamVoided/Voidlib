package org.teamvoided.voidmill.sign

import net.minecraft.block.WoodType
import net.minecraft.block.sign.WallHangingSignBlock

@Suppress("unused")
class VoidWallHangingSignBlock(woodType: WoodType, settings: Settings) :
    WallHangingSignBlock(woodType, settings.solid()), VoidSign

