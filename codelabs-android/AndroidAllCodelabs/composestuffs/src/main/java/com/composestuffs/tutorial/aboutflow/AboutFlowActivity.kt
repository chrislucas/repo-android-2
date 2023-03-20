package com.composestuffs.tutorial.aboutflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composestuffs.tutorial.aboutflow.ui.theme.AndroidAllCodelabsTheme


/*
    https://medium.com/@ahmadkazimi/all-you-need-to-know-about-kotlin-flow-part-1-1660ae471441

    
        https://medium.com/@ahmadkazimi/all-you-need-to-know-about-kotlin-flow-part-3-8c549648317a

        State Flow - State Holder observable Flow
        definicao nao formal: "Um observador de estado de fluxo que EMITE o estado atual e atualizacoes
        de estado para um COLETOR"

        O autor compara o StateFlow com o LiveData do Android porém sem "o mecanismo de conhecimento de ciclo
        de vida".

        A estrutura do StateFlow mantem um unico valor de estao. e isso torna-o um HotFlow

            - ColdFlow: Um 'Cold Stream' nao comeca a produzir valor ate que alguem comece a coletar
            dados dele

            - HotFlow: Um HotStream inicia a produzir valor imediatamente
 */

class AboutFlowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAllCodelabsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidAllCodelabsTheme {
        Greeting("Android")
    }
}