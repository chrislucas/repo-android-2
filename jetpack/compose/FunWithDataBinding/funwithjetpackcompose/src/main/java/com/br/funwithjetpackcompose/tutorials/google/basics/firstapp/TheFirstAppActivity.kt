package com.br.funwithjetpackcompose.tutorials.google.basics.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.br.funwithjetpackcompose.tutorials.google.basics.firstapp.ui.theme.FunWithDataBindingTheme

/*

    TODO - ler sobre layout inspector em Jetpack compose

        - add id compose component android
        - Using Compose in Views
            - https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/compose-in-views
        -  How to find elements for Testing Jetpack Compose Apps
            - https://www.linkedin.com/pulse/how-find-elements-testing-jetpack-compose-apps-rafael-muniz-vieira/

        - How to use xml:s android:id in Jetpack compose?
            - https://stackoverflow.com/questions/74730550/how-to-use-xmls-androidid-in-jetpack-compose

    ---- --------------------------------------------------------------------

    How to use xml:s android:id in Jetpack compose?
    https://stackoverflow.com/questions/74730550/how-to-use-xmls-androidid-in-jetpack-compose

    fun Modifier.layoutId(layoutId: Any)
    https://composables.com/compose-ui/layoutid


    ---- --------------------------------------------------------------------
    TODO
    https://developer.android.com/courses/android-basics-compose/unit-1

        - Notas sobre jetpack composw
            - Essa tecnologia e construida ao entorno de composable functions, e essas funcoes
            permitem definir a UI de forma programatica descrevendo como a parte visual deve parecer.
            - Uma composable function é uma funcao com a anotacao @Composable

            - Composable function pode aceitar argumentos (Obvio). Podemos usa-los para construir a
            logica de descrever ou modificar a UI

       - Regras para nomes de composable functions
            - Deve ser um substantivo
                - Exemplo DoneButton()
            - Nao pode ser
                - Um vero
                    - DrawTextField()
                - Preposicao substantiva
                    - TextFieldWithLink()
                - Adjetivo
                    - Brigh()
            - Substantivos pode ser prefixados por adjetivos descritivos
                - RoundIcon()
    TODO
    https://developer.android.com/courses/pathways/android-basics-compose-unit-1-pathway-3
 */
class TheFirstAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Home(MESSAGE)
        }
    }
}

private val MESSAGE = "Ola, Chris !!!"

@Composable
fun Home(message: String) {
    FunWithDataBindingTheme {
        /*
            https://developer.android.com/codelabs/basic-android-kotlin-compose-text-composables#4
            - O container Surface usado a cor de fundo (background) do tema
         */
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GreetingText(message)
        }
    }
}

private val INIT_TEXT_SIZE = 50

@Composable
private fun GreetingText(
    message: String,
    modifier: Modifier = Modifier
) {
    Text(
        message,
        modifier = modifier.layoutId("greeting_text"),
        fontSize = INIT_TEXT_SIZE.sp
    )
}


@Composable
fun SliderTextSize() {
    /*
    val sliderPositionObserver by remember {
        mutableFloatStateOf(INIT_TEXT_SIZE.toFloat())
    }

     */
}

@Preview(
    showBackground = true,
    name = "Home Preview",
    showSystemUi = false
)
@Composable
fun HomePreview() {
    Home(MESSAGE)
}