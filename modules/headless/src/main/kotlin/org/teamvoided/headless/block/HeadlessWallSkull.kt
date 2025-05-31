package org.teamvoided.headless.block

import net.minecraft.block.WallSkullBlock
import net.minecraft.block.entity.BlockEntityType

open class HeadlessWallSkull(skullType: HeadlessSkullType, settings: Settings) : WallSkullBlock(skullType, settings),
    Headless {
    init {
        BlockEntityType.SKULL.addSupportedBlock(this)
    }
}
