package org.teamvoided.voidlib.client

import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.server.packs.PackType
import org.teamvoided.voidlib.client.impl.event.ResourceInvalidator

object VoidlibClient {

    fun init() {
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(ResourceInvalidator)
    }

}