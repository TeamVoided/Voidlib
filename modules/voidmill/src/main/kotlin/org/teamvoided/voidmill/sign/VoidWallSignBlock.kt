package org.teamvoided.voidmill.sign

import net.minecraft.block.WoodType
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.block.sign.WallSignBlock

@Suppress("unused")
class VoidWallSignBlock(woodType: WoodType, settings: Settings) : WallSignBlock(woodType, settings.solid()), VoidSign {
    init {
        BlockEntityType.SIGN.addSupportedBlock(this)
    }
}

