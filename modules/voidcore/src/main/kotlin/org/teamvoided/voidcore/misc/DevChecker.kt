@file:Suppress("unused")

package org.teamvoided.voidcore.misc

import net.fabricmc.loader.api.FabricLoader

fun loaderIsDev() = FabricLoader.getInstance().isDevelopmentEnvironment

private var DEV_ENV = FabricLoader.getInstance().isDevelopmentEnvironment
fun isDev() = DEV_ENV

fun toggleDevEnv() {
    DEV_ENV = !DEV_ENV
}

fun setDevEvn(value: Boolean) {
    DEV_ENV = value
}