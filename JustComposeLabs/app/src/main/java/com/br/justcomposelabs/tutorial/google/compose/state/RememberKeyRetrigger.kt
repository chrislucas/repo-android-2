package com.br.justcomposelabs.tutorial.google.compose.state

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Matrix
import android.graphics.Shader
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.justcomposelabs.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.br.justcomposelabs.tutorial.google.compose.recompositionhighlighter.recomposeHighlighter

/**
 *
 * @see com.br.justcomposelabs.tutorial.google.performance.followbestpractice.ContactListWrong
 *
 * Retrigger remember calculations when keys change
 * https://developer.android.com/develop/ui/compose/state#retrigger-remember
 *
 * - remember armazena valores ate que ele saia da composicao.
 * É possivel invalidar o valor armazenado.
 *

 */


data class ImageResourceUiState(val id: Int)

class ImageResourceViewModel : ViewModel() {
    private val resources = listOf(
        R.drawable.kitkat,
        R.drawable.dandelion_chocolate,
        R.drawable.lollipop,
        R.drawable.marshmallow,
        R.drawable.nougat,
        R.drawable.oreo,
        R.drawable.rainbow,
        R.drawable.gingerbread,
        R.drawable.honeycomb,
        R.drawable.jellybean,
        R.drawable.rainbow,
        R.drawable.feathertop,
        R.drawable.hero,
    )

    private val resourceId = flow {
        while (true) {
            val idx = Random.nextInt(resources.size)
            emit(resources[idx])
            delay(500000L)
        }
    }

    private val _uiState = MutableStateFlow(
        ImageResourceUiState(resources[0])
    )
    val uiState: StateFlow<ImageResourceUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            resourceId.collect {
                _uiState.update { currentState ->
                    currentState.copy(id = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BackgroundBanner(viewModel: ImageResourceViewModel = viewModel()) {
    val backgroundResource by viewModel.uiState.collectAsStateWithLifecycle()
    val res = LocalResources.current
    val ctx = LocalContext.current
    val (brush, name) = remember(keys = arrayOf(backgroundResource)) {
        /*
           - O metodo remember aceita como parametros key ou keys e se qualquer uma dessas keys mudar, a
           proxima vez que a funcao sofrer recomposicao, remember invalida o cache e executa a funcao
           lambda passada para remember novamente.


            https://developer.android.com/develop/ui/compose/state#retrigger-remember
            Esse trecho tem inspiracao na documentacao acima.

            a funcao remember armazena a instancia de ShaderBrush pois é custoso ficar recriando-a
            em toda recomposicao.

            Para fins de estudo fiz diferente da documentacao e quis armazenar uma data class
            inteira com o remember. Essa data class contem um atributo que armazena o id do
            RESOURCE/Drawable

            Criei também uma viewmodel que emite uma nova data class com um novo ID para resource
            para provocar a mudanca no parametro keys passado para funcao remember.
         */
        val drawable = ContextCompat.getDrawable(
            ctx, backgroundResource.id
        )

        val bitmap = drawable?.toBitmap(
            width = drawable.intrinsicWidth,
            height = drawable.intrinsicHeight,
            config = Bitmap.Config.ARGB_8888
        ) ?: ImageBitmap.imageResource(res, R.drawable.feathertop).asAndroidBitmap()

        val matrix = Matrix()
        matrix.postTranslate(bitmap.width / 4f, bitmap.height / 4f)
        val brush = ShaderBrush(
            BitmapShader(
                bitmap,
                Shader.TileMode.MIRROR,
                Shader.TileMode.MIRROR
            ).apply {
                setLocalMatrix(matrix)
            })
        brush to res.getResourceName(backgroundResource.id).substringAfterLast("/")
    }

    val countingRecompositions = remember { mutableLongStateOf(0) }
    val callbackRecomposition = { value: Long ->
        countingRecompositions.longValue = value
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Resource name: $name.\nRecomposition:${countingRecompositions.longValue}",
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color(136, 162, 255, 255),
                fontSize = 33.sp
            ),
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color(218, 76, 76, 255),
                    shape = RoundedCornerShape(3.dp)
                )
                .recomposeHighlighter(turnOn = true, callback = callbackRecomposition)
        )
    }
}


@SuppressLint("ComposableNaming")
@Composable
private fun RememberImageResourceUiState(id: Int) = remember(id) {
    /*
        Expor uma funcao que cria uma instance que sobrevive a recomposicao é um pattern em
        Compose.

        RememberImageResourceUiState recebe um id que é usado como key na funcao remember. Se
        o parametro mudar, o app precisa recriar ImageResourceUiState com o novo id.

        - Compose usa o metodo equals para decidir se a chave mudou e assim invalidar o valor
        armazenado no cache
     */
    ImageResourceUiState(id)
}

/*
    Store state with keys beyond recomposition
    https://developer.android.com/develop/ui/compose/state#store-state

    RememverSaveable api é um wrapper da funcao rememeber que armazena
    uma estrutura de dados num Bundle

    - Essa api permite que o conteudo armazenado sobreviva nao somente
    a recompositions, mas tambem a recriacao de activities e iniciacao de destruicao de processos
    iniciadas pelo sistema

    - RememberSaveable recebe um parametro "input" com o mesmo proposito que remember recebe
    o parametro "keys". O cache é invalidado quando qualquer valor passado por parametro mudar.

        - A proxima vez que o processo de recomposicao executar a funcao lambda passada como
        parametro sera executada
 */