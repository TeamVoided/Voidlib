package org.teamvoided.voidlib.api.events

import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory.createArrayBacked
import net.fabricmc.fabric.api.util.TriState
import net.minecraft.world.entity.SlotAccess
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ClickAction
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack

object InventoryItemEvents {

    /**
     * Callback for right-clicking on item from cursor.
     *
     * Returning {@link net.fabricmc.fabric.api.util.TriState#DEFAULT} will pass to vanilla behavior
     */
    @JvmField
    val ON_CLICKED_ON_OTHER: Event<OnClickedOnOtherCallback> =
        createArrayBacked(OnClickedOnOtherCallback::class.java) { listeners ->
            OnClickedOnOtherCallback { self, slot, clickAction, player ->
                for (callback in listeners) {
                    val returnValue = callback.interact(self, slot, clickAction, player)
                    if (returnValue != TriState.DEFAULT) {
                        return@OnClickedOnOtherCallback returnValue
                    }
                }
                TriState.DEFAULT
            }
        }

    /**
     * Callback for right-clicking on item in slot.
     *
     * Returning {@link net.fabricmc.fabric.api.util.TriState#DEFAULT} will pass to vanilla behavior
     *
     */
    @JvmField
    val ON_CLICKED: Event<OnClickedCallback> =
        createArrayBacked(OnClickedCallback::class.java) { listeners ->
            OnClickedCallback { self, other, slot, clickAction, player, carriedItem ->
                for (callback in listeners) {
                    val returnValue =
                        callback.interact(self, other, slot, clickAction, player, carriedItem)
                    if (returnValue != TriState.DEFAULT) {
                        return@OnClickedCallback returnValue
                    }
                }
                TriState.DEFAULT
            }
        }


    @FunctionalInterface
    fun interface OnClickedOnOtherCallback {
        fun interact(self: ItemStack, slot: Slot, clickAction: ClickAction, player: Player): TriState
    }

    @FunctionalInterface
    fun interface OnClickedCallback {
        fun interact(
            self: ItemStack,
            other: ItemStack,
            slot: Slot,
            clickAction: ClickAction,
            player: Player,
            carriedItem: SlotAccess,
        ): TriState
    }
}

