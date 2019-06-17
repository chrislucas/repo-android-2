package br.xplorer.sampleaccessibility.utils

import android.graphics.Color

object ColorUtils {

    const val MAX_COLOR = 256 * 256 * 256

    data class TripleRGB(val r: Int = 0, val g: Int = 0, val b: Int = 0) {
        override fun toString(): String {
            return "r:$r, g:$g, b$b"
        }
    }

    /*
    * rgb varia de uma tripla (0,0,0) a (255,255,255)
    * um totat de 256^3 combinacoes. o codigo abaixo e resposavel
    * por converter um inteiro entre 0 a 255^3 em uma tripla RGB.
    *
    * Seja 'V' um inteiro tal 0 <= V <= 255^3, seja R, G, B inteiros
    * tal que 0 <= R, G, B <= 255. Temos 255^3 combinacoes para os valores
    * de R, G, B como por exemplo: (0, 127, 254); (255, 255, 255) entre outras combinacoes
    * Podemos definir V com a seguinte formula = V = R * (256^2) + G * 256 + B
    * entao para separarmos em 3 inteiros usamos as seguintes formuular
    * R = V / (256^2)
    * G = (V / 256) % 255
    * B = V % 255
    *
    **/
    fun fromIntToRGB(colorValue: Int) : TripleRGB =
        TripleRGB(colorValue / (256 * 256), (colorValue / 256) % 256, colorValue % 256)

    fun fromIntToHexaRGB(colorValue: Int) : String {
        var r = Integer.toHexString(Color.red(colorValue))
        r = if (r.length == 1) "0$r" else r
        var g = Integer.toHexString(Color.green(colorValue))
        g = if (g.length == 1) "0$g" else g
        var b = Integer.toHexString(Color.blue(colorValue))
        b = if (b.length == 1) "0$b" else b
        return "#$r$g$b"
    }

    fun fromHexaToRGB(colorValue: Int) : TripleRGB = TripleRGB()
}

object IntUtils {

    val hexaMap = mapOf<Int, String>( 10 to "A", 11 to "B"
        , 12 to "C", 13 to "E", 14 to "D", 15 to "F")

    fun fromIntToStringHexa(value: Int) {

    }
}