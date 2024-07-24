package org.teamvoided.voidmill.sign

import net.minecraft.block.WoodType
import net.minecraft.block.sign.SignBlock

class VoidSignBlock(woodType: WoodType, settings: Settings) :
    SignBlock(woodType, settings.solid()), VoidSign

