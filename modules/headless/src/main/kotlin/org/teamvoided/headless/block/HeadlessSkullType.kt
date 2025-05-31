package org.teamvoided.headless.block

import net.minecraft.block.SkullBlock
import net.minecraft.util.Identifier

open class HeadlessSkullType(val type: Identifier) : SkullBlock.SkullType {
    init {
        SkullBlock.SkullType.TYPES[this.asString()] = this
    }

    override fun asString(): String = type.toString()
    override fun toString(): String = asString().uppercase()
}