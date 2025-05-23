@file:Suppress("unused")

package org.teamvoided.voidcore.helpers

import net.minecraft.util.math.Vec2f
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.Vec3i


// Vec2f
fun Vec2f.map(func: (Float) -> Float): Vec2f = Vec2f(func(this.x), func(this.y))
fun Vec2f(init: Number): Vec2f = Vec2f(init, init)
fun Vec2f(x: Number, y: Number): Vec2f = Vec2f(x.toFloat(), y.toFloat())

// Vec3d
fun Vec3d.map(func: (Double) -> Double): Vec3d = Vec3d(func(this.x), func(this.y), func(this.z))
fun Vec3d(init: Number): Vec3d = Vec3d(init, init, init)
fun Vec3d(x: Number, y: Number, z: Number): Vec3d = Vec3d(x.toDouble(), y.toDouble(), z.toDouble())

// Vec3i
fun Vec3i.map(func: (Int) -> Int): Vec3i = Vec3i(func(this.x), func(this.y), func(this.z))
fun Vec3i(init: Number): Vec3i = Vec3i(init, init, init)
fun Vec3i(x: Number, y: Number, z: Number): Vec3i = Vec3i(x.toInt(), y.toInt(), z.toInt())

