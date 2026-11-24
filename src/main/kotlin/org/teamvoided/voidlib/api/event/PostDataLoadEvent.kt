package org.teamvoided.voidlib.api.event
import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory
import net.minecraft.server.MinecraftServer

fun interface PostDataLoadEvent {

    fun dataLoaded(server: MinecraftServer)

    companion object {

        @JvmStatic
        val DATA_LOADED: Event<PostDataLoadEvent> =
            EventFactory.createArrayBacked(PostDataLoadEvent::class.java) { callbacks ->
                PostDataLoadEvent { server ->
                    for (callback in callbacks) {
                        callback.dataLoaded(server)
                    }
                }
            }

    }
}