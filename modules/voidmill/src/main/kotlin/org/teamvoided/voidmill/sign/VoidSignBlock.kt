package org.teamvoided.voidmill.sign

import net.minecraft.block.WoodType
import net.minecraft.block.sign.SignBlock

@Suppress("unused")
class VoidSignBlock(woodType: WoodType, settings: Settings) :
    SignBlock(woodType, settings.solid()), VoidSign

