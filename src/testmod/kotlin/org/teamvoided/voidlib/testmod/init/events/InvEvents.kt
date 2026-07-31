package org.teamvoided.voidlib.testmod.init.events

import net.fabricmc.fabric.api.util.TriState
import net.minecraft.core.component.DataComponents
import net.minecraft.tags.ItemTags
import net.minecraft.world.entity.SlotAccess
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ClickAction
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

fun onClickedOnOther(self: ItemStack, slot: Slot, clickAction: ClickAction, player: Player): TriState {
    if (clickAction != ClickAction.SECONDARY) return TriState.DEFAULT
    if (!slot.allowModification(player)) return TriState.DEFAULT
    if (!slot.hasItem()) return TriState.DEFAULT
    if (!self.`is`(Items.DIAMOND)) return TriState.DEFAULT

    val slotItem = slot.item
    val model = Items.DIAMOND.defaultInstance[DataComponents.ITEM_MODEL]

    if (slotItem[DataComponents.ITEM_MODEL] == model) {
        slotItem.copyFrom(DataComponents.ITEM_MODEL, slotItem.prototype)
    } else {
        slotItem[DataComponents.ITEM_MODEL] = model
    }

    return TriState.TRUE
}

fun onClicked(
    self: ItemStack, other: ItemStack, slot: Slot, clickAction: ClickAction, player: Player, carriedItem: SlotAccess,
): TriState {
    if (clickAction != ClickAction.PRIMARY) return TriState.DEFAULT
    if (!slot.allowModification(player)) return TriState.DEFAULT
    if (other.isEmpty) return TriState.DEFAULT
    if (!self.`is`(Items.APPLE)) return TriState.DEFAULT
    if (!other.`is`(ItemTags.SAPLINGS)) return TriState.DEFAULT

    carriedItem.set(Items.GOLDEN_APPLE.defaultInstance)

    return TriState.TRUE

}