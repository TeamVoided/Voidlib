package org.teamvoided.voidmill.sign

import net.minecraft.block.WoodType
import net.minecraft.block.sign.WallSignBlock

class VoidWallSignBlock(woodType: WoodType, settings: Settings) :
    WallSignBlock(woodType, settings.solid()), VoidSign

