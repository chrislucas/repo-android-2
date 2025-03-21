package com.br.funwithdatabinding.view.features.books.android2dgraphics

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/*
    https://varun.ca/polar-coords/
*/


fun fromPolarToCartesian(
    radian: Double,
    radius: Double
): Pair<Double, Double> = Pair(radius * cos(radian), radius * sin(radian))


fun toDegree(radian: Double) = radian * 180.0 / PI

fun testToDegree() {
    generateSequence(1.0) { it + 0.1 }
        .takeWhile { it == PI }
        .map { radian -> radian to toDegree(radian) }
        .toList()
        .let(::println)

    println(toDegree(PI * .5))
}

fun toRadian(degree: Double) = degree * PI / 180.0


fun main() {
    testToDegree()
}