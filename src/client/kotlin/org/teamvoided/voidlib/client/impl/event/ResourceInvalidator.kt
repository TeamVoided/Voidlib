package org.teamvoided.voidlib.client.impl.event

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.ResourceManagerReloadListener
import org.teamvoided.voidlib.Voidlib
import org.teamvoided.voidlib.client.api.InvalidateResourcesCallback

object ResourceInvalidator : ResourceManagerReloadListener, IdentifiableResourceReloadListener {

    override fun getFabricId() = Voidlib.id("resource_invalidator")

    override fun onResourceManagerReload(resourceManager: ResourceManager) {
        InvalidateResourcesCallback.EVENT.invoker().invalidate()
    }

}