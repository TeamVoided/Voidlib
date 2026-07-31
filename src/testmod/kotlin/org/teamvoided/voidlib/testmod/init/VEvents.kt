package org.teamvoided.voidlib.testmod.init

import org.teamvoided.voidlib.api.events.InventoryItemEvents
import org.teamvoided.voidlib.api.events.PostUseItemEvents
import org.teamvoided.voidlib.testmod.init.events.onClicked
import org.teamvoided.voidlib.testmod.init.events.onClickedOnOther
import org.teamvoided.voidlib.testmod.init.events.*

object VEvents {

    fun init() {
        InventoryItemEvents.ON_CLICKED.register(::onClicked)
        InventoryItemEvents.ON_CLICKED_ON_OTHER.register(::onClickedOnOther)
        PostUseItemEvents.POST_USE.register(::postUse)
        PostUseItemEvents.POST_USE_ON_BLOCK.register(::postUseOnBlock)
        PostUseItemEvents.POST_USE_ON_ENTITY.register(::postUseOnEntity)
        PostUseItemEvents.POST_FINISH_USING.register(::postFinishUsing)
        PostUseItemEvents.POST_RELEASE_USING.register(::postReleaseUsing)
    }

}