package org.teamvoided.voidmill.sign

import net.minecraft.block.WoodType
import net.minecraft.block.sign.WallSignBlock
import net.minecraft.util.Identifier

@Suppress("unused")
class VoidWallSignBlock(override val texture: Identifier, woodType: WoodType, settings: Settings) :
    WallSignBlock(woodType, settings.solid()), VoidSign

