@file:Suppress("unused")

package org.teamvoided.voidcore.helpers.mc

import net.minecraft.registry.Holder
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

fun <T> Registry<T>.registerHolder(id: Identifier, entry: T): Holder.Reference<T> =
    Registry.registerHolder<T>(this, id, entry)

fun <T> Registry<T>.registryHolder(id: RegistryKey<T>, entry: T): Holder.Reference<T> =
    Registry.registryHolder<T>(this, id, entry)


fun <T> Registry<T>.register(id: Identifier, entry: T): T {
    Registry.register(this, id, entry)
    return entry
}

fun <T> Registry<T>.register(id: RegistryKey<T>, entry: T): T {
    Registry.register(this, id, entry)
    return entry
}

fun <T> RegistryKey<Registry<T>>.key(id: Identifier): RegistryKey<T> = RegistryKey.of(this, id)
fun <T> RegistryKey<Registry<T>>.tag(id: Identifier): TagKey<T> = TagKey.of(this, id)


