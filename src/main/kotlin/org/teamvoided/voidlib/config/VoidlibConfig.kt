package org.teamvoided.voidlib.config

import me.fzzyhmstrs.fzzy_config.annotations.Action
import me.fzzyhmstrs.fzzy_config.annotations.RequiresAction
import me.fzzyhmstrs.fzzy_config.config.Config
import me.fzzyhmstrs.fzzy_config.util.Translatable
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedBoolean
import org.teamvoided.voidlib.Voidlib.MODID
import org.teamvoided.voidlib.Voidlib.id

@Suppress("unused")
class VoidlibConfig : Config(id(MODID)) {

    @RequiresAction(Action.RESTART)
    @Translatable.Desc("Enables or disables all custom content added by this lib. e.g. Custom particles, blocks etc.")
    var enableContent = ValidatedBoolean(true)

}