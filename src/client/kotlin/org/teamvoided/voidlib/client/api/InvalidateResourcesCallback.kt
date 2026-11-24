package org.teamvoided.voidlib.client.api

import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory

/**
 * Event for Invalidated caches for readable resources. Called when client resources are reloaded. Does not guarantee consistent order. Should only be used to invalided caches or similar actions.
 */
@FunctionalInterface
fun interface InvalidateResourcesCallback {

    fun invalidate()

    companion object {

        @JvmStatic
        val EVENT: Event<InvalidateResourcesCallback> =
            EventFactory.createArrayBacked(InvalidateResourcesCallback::class.java) { callbacks ->
                InvalidateResourcesCallback { callbacks.forEach { it.invalidate() } }
            }

    }
}