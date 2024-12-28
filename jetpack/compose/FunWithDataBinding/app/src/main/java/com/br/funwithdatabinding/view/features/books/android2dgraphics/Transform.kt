package com.br.funwithdatabinding.view.features.books.android2dgraphics


class Transform {
    var move = 0.0f to 0.0f
    var scale = 1.0f to 1.0f

    operator fun Pair<Float, Float>.plusAssign(newPair: Pair<Float, Float>) {
        move = Pair(move.first + newPair.first, move.second + newPair.second)
    }

    operator fun Pair<Float, Float>.timesAssign(newPair: Pair<Float, Float>) {
        move = Pair(move.first * newPair.first, move.second * newPair.second)
    }

    fun moving(newMove: Pair<Float, Float>) {
        move += newMove
    }

    fun scaling(newMove: Pair<Float, Float>) {
        move *= newMove
    }

    /*
           Metodos para a transformacao de coordenada logicas para de dispositivos
     */

    fun transformX(x: Float) = move.first + scale.first + x

    fun transformY(y: Float) = move.second + scale.second + y
}