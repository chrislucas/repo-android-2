package com.br.justcomposelabs.tutorial.google.compose.sideffects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.tutorial.google.compose.sideffects.ui.theme.JustComposeLabsTheme

/*
    https://developer.android.com/develop/ui/compose/side-effects

    - Side-effects
        - É a mudanca de estado do app que ocorre fora do escopo de uma Composable Functions
        - Devido a composable lifeycle e propriedades tais como unpredictable recompositions,
        execucao de recomposiciones de uma composable em diferentes ordens, ou recompositions
        que podem ser descartadas, composables devem ser livres de side-effect


        - Entretando, as vezes side-effects sao necessarios: Disparar um evento que
        mostre uma snackbar ou navegar para uma tela dado uma certa condicao

        - Esse tipo de acao deve ser chamada a partir de um ambiente de controle
        que esteja ciente do lifecycle de uma composable

    https://github.com/android/snippets/blob/39218ccca3e9852e325c1ef5358489d3778fd666/compose/snippets/src/main/java/com/example/compose/snippets/sideeffects/SideEffectsSnippets.kt#L81-L105
 */
class SideEffectsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val paddingModifier = Modifier.padding(innerPadding)
                }
            }
        }
    }
}

@Composable
fun PulseEffect() {
    var pulseRateMs by remember { mutableStateOf(500L) }
    val alpha = remember { Animatable(1f) }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustComposeLabsTheme {

    }
}