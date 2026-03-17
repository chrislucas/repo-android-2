package com.br.funwithdatabinding.view.features.books.android2dgraphics.chp8

/*
    TODO revisitar esse assunto
 */
class Transform(
    private var moveX: Float,
    private var moveY: Float,
    private var scaleX: Float,
    private var scaleY: Float,
) {
    fun setMove(move: Pair<Float, Float>) {
        move.let { (x, y) ->
            moveX = x
            moveY = y
        }
    }

    fun setScale(scale: Pair<Float, Float>) {
        scale.let { (x, y) ->
            scaleX = x
            scaleY = y
        }
    }
 }