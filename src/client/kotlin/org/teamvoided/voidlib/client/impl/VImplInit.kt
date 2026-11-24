package org.teamvoided.voidlib.client.impl

import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.server.packs.PackType
import org.teamvoided.voidlib.client.impl.event.ResourceInvalidator

object VImplInit {

    fun init() {
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(ResourceInvalidator)
    }

}