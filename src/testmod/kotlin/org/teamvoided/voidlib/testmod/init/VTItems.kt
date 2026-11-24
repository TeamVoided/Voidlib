package org.teamvoided.voidlib.testmod.init

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.*
import net.minecraft.world.item.crafting.Ingredient
import org.teamvoided.voidlib.testmod.VTest
import org.teamvoided.voidlib.util.register
import org.teamvoided.voidlib.world.item.EquipableItem
import org.teamvoided.voidlib.world.item.VToolMaterial

object VTItems {

    val KEY_MAT = VToolMaterial.copyOf(Tiers.IRON, ItemTags.BUTTONS)
    val KILL_MAT = VToolMaterial.copyOf(Tiers.GOLD, Ingredient.of(Items.WITHER_SPAWN_EGG))

    val KEY = register(
       "key", EquipableItem(
            Item.Properties()
                .attributes(AxeItem.createAttributes(KEY_MAT, 6F, -3.2F))
        )
    )
    val KILL_ITEM = register(
        "kill_item", SwordItem(
            KILL_MAT, Item.Properties()
                .attributes(SwordItem.createAttributes(KILL_MAT, 6, -2F))
        )
    )

    fun init() = Unit

    fun register(name: String, item: Item): Item {
        return BuiltInRegistries.ITEM.register(VTest.id(name), item)
    }

}