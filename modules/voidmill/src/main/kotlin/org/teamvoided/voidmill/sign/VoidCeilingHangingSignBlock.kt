package org.teamvoided.voidmill.sign

import net.minecraft.block.WoodType
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.block.sign.CeilingHangingSignBlock

@Suppress("unused")
class VoidCeilingHangingSignBlock(woodType: WoodType, settings: Settings) :
    CeilingHangingSignBlock(woodType, settings.solid()), VoidSign {
    init {
        BlockEntityType.HANGING_SIGN.addSupportedBlock(this)
    }
}

