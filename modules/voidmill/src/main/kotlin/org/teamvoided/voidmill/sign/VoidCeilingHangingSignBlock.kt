package org.teamvoided.voidmill.sign

import net.minecraft.block.WoodType
import net.minecraft.block.sign.CeilingHangingSignBlock

class VoidCeilingHangingSignBlock(woodType: WoodType, settings: Settings) :
    CeilingHangingSignBlock( woodType, settings.solid()), VoidSign

