package org.teamvoided.voidcore.helpers.mc

import net.minecraft.text.ClickEvent
import net.minecraft.text.HoverEvent
import net.minecraft.text.Style

fun Style.clickEvent(action: ClickEvent.Action, value: String): Style = this.withClickEvent(ClickEvent(action, value))
fun <T> Style.hoverEvent(action: HoverEvent.Action<T>, value: T): Style = this.withHoverEvent(HoverEvent(action, value))

