package com.example.feature.android.api.v33.shaders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidallcodelabs.R

/**
 *  Android Graphics Shading Language (AGSL)
 *  https://developer.android.com/develop/ui/views/graphics/agsl
 *
 *  Resumo:
 *
 *  AGSL e usado a partir do android 13 e acima para definir o comporabmento programavel de objetos
 *  RuntimeShader (https://developer.android.com/reference/android/graphics/RuntimeShader)
 *
 *  - RuntimeShader:
 *      - Uma RuntimeShader calcula uma cor por pixel com base na saida de funcao AGSL definida pelo usu;ario
 *      - AGSL (Android Grapgics Shading Language)
 *          - A sintaxe AGSL e similar a OpenGL ES shading language com algumas importantes diferencas
 *              - Com uma GPU shagin language, estamos programando um estagio do pipeline da GPU
 *              - COm AGSL, programamos um estagio do pipeline de desenho das classes Canvas e RenderNode
 *
 *  - Theory of Ops
 *      - AGSL effect existe como parte do pipeline gráfico do android. Quando o android emite
 *      uma operacao de desenho acelerado por GPU, ele monta um unico shader de fragmento de GPU
 *      para fazer o trabalho
 *
 *      - Esse shader geralmente inclue muitos pedacos, tais com:
 *          - Avaliar se um pixel esata dentro ou fora da FORMA á ser desenhada (ou na borda, onde deve ser aplicado
 *          o anti-aliasing)
 *
 *          - Color space conversion code, as part of Android color management
 *
 *   - Sintaxe basica
 *      - AGSL e GLSL sao DSL C-Style, tipos como bool e int sao equivalentes em C, ha  ipos adicionais
 *      para suportar vetores e matrizes
 *
 *      - aGSL suporta funcoes, cada programa shader comeca com uma funcao main.
 *      - User defined function sao suportadas, sem suporte a recursao de qualquer tipo
 *      - valores passados para funcoes sao copiadas para os parametros quando a funcao e chamada
 *      - resultados de funcoes sao copiados, isso é determinado pelos qualificadores in, out e inout
 *
 *  Blurring the Lines
 *  https://medium.com/androiddevelopers/blurring-the-lines-4fd33821b83c
 *
 *  AGSL: Made in the Shade(r)
 *  https://medium.com/androiddevelopers/agsl-made-in-the-shade-r-7d06d14fe02a
 */


class AndroidGraphicsShadingLanguageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_graphics_shading_language)
    }
}