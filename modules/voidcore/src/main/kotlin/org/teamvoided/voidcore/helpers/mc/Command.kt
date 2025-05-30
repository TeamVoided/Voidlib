package org.teamvoided.voidcore.helpers.mc

import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.tree.CommandNode
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

fun <S> CommandNode<S>.childOf(node: CommandNode<S>): CommandNode<S> {
    node.addChild(this)
    return this
}

fun <S, Q : ArgumentBuilder<S, Q>> ArgumentBuilder<S, Q>.buildChildOf(node: CommandNode<S>): CommandNode<S> {
    return this.build().childOf(node)
}

fun ServerCommandSource.feedback(msg: String, tellOps: Boolean = false) = sendFeedback({ Text.literal(msg) }, tellOps)
fun ServerCommandSource.message(msg: String) = sendSystemMessage(Text.literal(msg))
fun ServerCommandSource.error(msg: String) = sendError(Text.literal(msg))

