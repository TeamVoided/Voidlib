package org.teamvoided.voidmill.sign

import net.minecraft.block.WoodType
import net.minecraft.block.sign.SignBlock
import net.minecraft.util.Identifier

@Suppress("unused")
class VoidSignBlock(override val texture: Identifier, woodType: WoodType, settings: Settings) :
    SignBlock(woodType, settings.solid()), VoidSign

