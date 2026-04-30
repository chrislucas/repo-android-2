package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp8

/*
    Transformation Operation: Translation, scaling, rotation, skewing
     - Translation: move the canvas by a certain distance in x and y direction
     - Scaling: scale the canvas by a certain factor in x and y direction
     - Rotation: rotate the canvas by a certain angle
     - Skewing: skew the canvas by a certain angle in x and y direction (Skew = distorcer)
        - distorcer a tela por um determinado ângulo nas direções x e y

    Esta classe implementa um sistema de mapeamento entre espaços de coordenadas:
    1. Espaço Lógico (Mundo): Onde definimos os pontos de forma abstrata (ex: 0.0 a 1.0).
    2. Espaço do Dispositivo (Tela): Onde os pixels realmente vivem.

    O mapeamento é feito através de Transformações Afins (escala e translação):
    - Escala (scale): Multiplica a coordenada lógica pelo tamanho da tela. No Android,
      o scaleY costuma ser negativo para inverter o eixo Y (matemático vs tela).
    - Translação (move): Adiciona um deslocamento para posicionar a origem (ex: canto inferior esquerdo).

    Fórmula: P_tela = (P_logico * Escala) + Translação
 */
class TransformationOperation(
    private val move: Pair<Float, Float> = 0f to 0f,
    private val scale: Pair<Float, Float> = 1f to 1f,
) {
    fun withMove(
        moveX: Float,
        moveY: Float,
    ) = TransformationOperation(moveX to moveY, scale)

    fun withScale(
        scaleX: Float,
        scaleY: Float,
    ) = TransformationOperation(move, scaleX to scaleY)

    fun transformX(x: Float): Float = x * scale.first + move.first

    fun transformY(y: Float): Float = y * scale.second + move.second
}
