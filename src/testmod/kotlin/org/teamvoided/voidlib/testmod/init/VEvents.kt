package org.teamvoided.voidlib.testmod.init

import org.teamvoided.voidlib.api.events.InventoryItemEvents
import org.teamvoided.voidlib.testmod.init.events.onClicked
import org.teamvoided.voidlib.testmod.init.events.onClickedOnOther

object VEvents {

    fun init() {
        InventoryItemEvents.ON_CLICKED.register(::onClicked)
        InventoryItemEvents.ON_CLICKED_ON_OTHER.register(::onClickedOnOther)
    }

}