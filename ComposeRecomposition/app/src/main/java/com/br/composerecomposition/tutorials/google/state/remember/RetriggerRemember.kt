package com.br.composerecomposition.tutorials.google.state.remember

import android.graphics.BitmapShader
import android.graphics.Shader
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.composerecomposition.R
import androidx.compose.ui.platform.LocalResources

/*
    Retrigger remember calculations when keys change
    https://developer.android.com/develop/ui/compose/state#retrigger-remember

    - A funcao remember é util para fazer valores MutableState sobreviverem a recomposicoes
        - Essa funcao aceita uma funcao lambda como parametro e quando executada a primeira vez ela
        armazena o resultado, Durante a recomposicao a funcao remember retorna o ultimo valor
        armazenado.
    - Além de armazenamento em cache, podemos armazenar qualquer objeto ou resultado que seja
    custoso, para nao ter que repetir tal operacao durante a recomposicao

    - um exemplo é usar o remember para criar um  objeto ShaderBrush. Essa é uma operacao custosa
 */



@Preview(showBackground = true)
@Composable
fun DrawableComponent(@DrawableRes drawableResourceId: Int = R.drawable.donut) {
    val resource = LocalResources.current
    val brush = remember {
        ShaderBrush(
            BitmapShader(
                ImageBitmap.imageResource(resource, drawableResourceId).asAndroidBitmap(),
                Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT
            )
        )
    }

    Box(
        modifier = Modifier.size(300.dp)
            .background(brush)
    )
}


/*
    Exemplo usando o parametro keys da funcao remember. Caso um dos elementos
    do array keys modificar a proxima recomposicao invalida o cache da funcao
    remember
 */
@Preview
@Composable
fun InvalidateCacheRemember(@DrawableRes drawableResourceId: Int = R.drawable.cupcake) {
    /*
        remember armazena o valor até que ele saia da composicao, entretanto é possível
        invalidar o valor armazenado.

        - O metodo remember recebe os parametros key ou keys. Se alguma dessas keys mudar, a proxima
        vez que a funcao recompor, remember invalida o cache e executa a funcao lambda novamente.


     */

    val res = LocalResources.current
    val brush = remember(keys = arrayOf(drawableResourceId)) {
        ShaderBrush(
            BitmapShader(
                ImageBitmap.imageResource(res, drawableResourceId).asAndroidBitmap(),
                Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT,
            )
        )
    }

    Box(
        modifier = Modifier.size(300.dp)
            .background(brush)
    )
}


/*
    Use an image as a brush
    https://developer.android.com/develop/ui/compose/graphics/draw/brush#image-as-brush
 */


@Preview(showBackground = true)
@Composable
fun GraphicsDogImageBrush() {
    val imageBrush = ShaderBrush(
        ImageShader(
            ImageBitmap.imageResource(id = R.drawable.dog)
        )
    )


    /*
        Modifier.requiredSize vs size
     */

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {

        Box(
            modifier = Modifier
                .size(300.dp)
                .background(imageBrush)
                .align(Alignment.CenterHorizontally)
        )

        Canvas(
            onDraw = {
                drawCircle(imageBrush)
            }, modifier = Modifier
                .size(300.dp)
                .padding(start = 2.dp, end = 2.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Content",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                brush = imageBrush,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp
            ),
            textAlign = TextAlign.Center
        )
    }

}
