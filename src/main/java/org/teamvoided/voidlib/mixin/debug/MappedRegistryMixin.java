package org.teamvoided.voidlib.mixin.debug;

import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

import static org.teamvoided.voidlib.Voidlib.config;
import static org.teamvoided.voidlib.util.HelpersKt.isDev;

@Mixin(MappedRegistry.class)
public abstract class MappedRegistryMixin<T> {

    @Shadow
    @Final
    ResourceKey<? extends Registry<T>> key;

    @Shadow
    @Final
    private Map<ResourceLocation, Holder.Reference<T>> byLocation;

    @Shadow
    @Final
    private Map<T, Holder.Reference<T>> byValue;

    @Shadow
    @Final
    private static Logger LOGGER;

    @Inject(method = "register", at = @At(value = "INVOKE", target = "Ljava/util/Map;containsKey(Ljava/lang/Object;)Z", ordinal = 0))
    void throwOrWarn(ResourceKey<T> resourceKey, T object, RegistrationInfo registrationInfo, CallbackInfoReturnable<Holder.Reference<T>> cir) {
        if (byLocation.containsKey(resourceKey.location())) {
            voidlib$throwInDev("Adding duplicate key '" + resourceKey.location() + "' to registry '" + key.location() + "'");
        }
        if (byValue.containsKey(object)) {
            voidlib$throwInDev("Adding duplicate value [original: '" + object + "', duplicate: '" + resourceKey.location() + "'] to registry '" + key.location() + "'");
        }
    }

    @Unique
    private static void voidlib$throwInDev(String message) {
        if (isDev() && config.devConfig.crashOnDuplicateRegistration) {
            throw new IllegalStateException(message);
        }
        LOGGER.error(message);
    }

}