package org.teamvoided.voidmill.sign

import net.minecraft.block.WoodType
import net.minecraft.block.sign.WallHangingSignBlock
import net.minecraft.util.Identifier

class VoidWallHangingSignBlock(woodType: WoodType, settings: Settings) :
    WallHangingSignBlock(woodType, settings.solid()), VoidSign

