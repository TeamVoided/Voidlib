package org.teamvoided.headless

import org.teamvoided.headless.ClientExampleSkulls.initExampleSkulls
import org.teamvoided.voidcore.misc.isDev

@Suppress("unused")
object HeadlessClient {
    fun init() {
        if (isDev()) initExampleSkulls()
    }
}
