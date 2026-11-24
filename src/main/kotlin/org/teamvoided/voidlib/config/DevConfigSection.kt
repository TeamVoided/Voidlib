package org.teamvoided.voidlib.config

import me.fzzyhmstrs.fzzy_config.config.ConfigSection

class DevConfigSection : ConfigSection() {

    @JvmField
    var crashOnDuplicateRegistration = true

}