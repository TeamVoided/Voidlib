package org.teamvoided.headless.block

import net.minecraft.block.SkullBlock
import net.minecraft.block.entity.BlockEntityType

open class HeadlessSkull(skullType: HeadlessSkullType, settings: Settings) : SkullBlock(skullType, settings), Headless {
    init {
        BlockEntityType.SKULL.addSupportedBlock(this)
    }
}
