package org.teamvoided.voidcore.helpers

fun <T : Any> MutableCollection<T>.addInPlace(element: T): MutableCollection<T> {
    this.add(element)
    return this
}

fun <T : Any> MutableCollection<T>.addAllInPlace(vararg element: T): MutableCollection<T> {
    this.addAll(element)
    return this
}

fun <T : Any> MutableCollection<T>.addAndReturn(element: T): T {
    this.add(element)
    return element
}

fun <T : Any> MutableCollection<T>.addAllReturn(vararg element: T): Array<out T> {
    this.addAll(element)
    return element
}
