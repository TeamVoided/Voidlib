@file:Suppress("unused")

package org.teamvoided.voidcore.mc.item

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.BlockTags.INCORRECT_FOR_IRON_TOOL
import net.minecraft.registry.tag.TagKey

@JvmRecord
data class VoidToolMaterial(
    val durability: Int,
    val miningSpeedMultiplier: Float,
    val attackDamage: Float,
    val incorrectForDropsBlocks: TagKey<Block>,
    val enchantability: Int,
    val repairIngredient: Ingredient
) : ToolMaterial {
    constructor(
        durability: Int, miningSpeedMultiplier: Float, attackDamage: Float,
        incorrectForDropsBlocks: TagKey<Block>, enchantability: Int, repairTag: TagKey<Item>
    ) : this(
        durability, miningSpeedMultiplier, attackDamage,
        incorrectForDropsBlocks, enchantability, Ingredient.ofTag(repairTag)
    )

    override fun getDurability(): Int = durability
    override fun getMiningSpeedMultiplier(): Float = miningSpeedMultiplier
    override fun getAttackDamage(): Float = attackDamage
    override fun getIncorrectForDropsBlocks(): TagKey<Block> = incorrectForDropsBlocks
    override fun getEnchantability(): Int = enchantability
    override fun getRepairIngredient(): Ingredient = repairIngredient

    companion object {
        fun weaponOnly(durability: Int, attackDamage: Float, enchantability: Int, repairTag: TagKey<Item>) =
            VoidToolMaterial(durability, 6F, attackDamage, INCORRECT_FOR_IRON_TOOL, enchantability, repairTag)
    }
}