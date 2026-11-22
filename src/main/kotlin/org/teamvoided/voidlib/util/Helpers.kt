@file:Suppress("unused")

package org.teamvoided.voidlib.util

import net.fabricmc.loader.api.FabricLoader
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import org.teamvoided.voidlib.Voidlib.MODID
import java.util.stream.Stream


fun isDev() = FabricLoader.getInstance().isDevelopmentEnvironment

fun <T : Any> isModHolder(holder: Holder<T>) = holder.`is` { it.location().namespace == MODID }

fun <T : Any> getModHolders(registry: Registry<T>): List<Holder.Reference<T>> = registry.listElements()
    .filter(::isModHolder)
    .toList()

fun <T : Any> getModEntries(registry: Registry<T>): List<T> = registry.listElements()
    .filter(::isModHolder)
    .map(Holder<T>::value)
    .toList()

// Name Backport
fun <T : Any> Registry<T>.listElements(): Stream<Holder.Reference<T>> = holders()

fun <V : Any, T : V> Registry<V>.register(id: ResourceLocation, entry: T): T = Registry.register(this, id, entry)
fun <V : Any, T : V> Registry<T>.registerHolder(id: ResourceLocation, entry: T): Holder.Reference<T> {
    return Registry.registerForHolder(this, id, entry)
}

fun <T : Any, R : Registry<T>> ResourceKey<R>.tag(id: ResourceLocation): TagKey<T> = TagKey.create(this, id)
fun <T : Any, R : Registry<T>> ResourceKey<R>.key(id: ResourceLocation): ResourceKey<T> = ResourceKey.create(this, id)
