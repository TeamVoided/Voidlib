@file:Suppress("unused")

package org.teamvoided.voidcore.helpers.mc

import net.minecraft.util.Identifier

fun mc(path: String): Identifier = Identifier.ofDefault(path)
fun createId(namespace: String, path: String): Identifier = Identifier.of(namespace, path)
