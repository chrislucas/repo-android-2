package com.br.funwithdatabinding.view.features.tutorials.google.uiarchitecture.managingstate.overview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.funwithdatabinding.view.features.tutorials.google.uiarchitecture.managingstate.overview.ui.theme.FunWithDataBindingTheme

class ManagingStateOverviewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Content(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/*
    Conceito Chave
    -   Composition:  A descricao de uma UI construida em Jetpack Compose quando ela executa uma
    composable function
        - inital compostion: cricao de uma Composition quando uma funcao composable eh executada
        pela primeira vez
        - recomposition: ocorre quando uma fnucao composable é re-executada com parametros
        diferentes, isso atualiza a composition

    - State in Composable (https://developer.android.com/develop/ui/compose/state#state-in-composables)


 */

@Composable
fun Content(name: String, modifier: Modifier = Modifier) {
    /*
        State And Composition
        https://developer.android.com/develop/ui/compose/state#state-and-composition
        - Compose é uma maneira declarativa de se criar componentes visuais e a unica
        forma de atualizar  esse componente é chamando a mesma funcao composable
        com argumentos novos

        - Argumentos passados para funcao sao a representacao do ESTADO do componente
            - Toda vez que um estado for atualizaco recomposition é acionado.
            - Composable function precisa ser executada explicitamente sendo informada
            do novo estado  para que a UI seja atualizada apropriadamente
        -

     */

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Nothing Happen $name!",
            modifier = modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Red
        )

        /*
             A UI construiada por composable nao atualizar da mesma maneira que uma View Based
             - Um exemplo é a TextField
                - Ela construida via XML (View) ao ser tocada por um usuario e tendo dados sendo inserido
                "automaticamente" atualiza seu estado. A View composable nao faz isso
                
                - No exemplo abaixo, a OutlinedTextField so atualiza quando o parametro "value" e atualizado
                
                - Isso é defvido a como composition e recomposition funciona em compose
         */

        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Não Atualiza") })
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
        Content("Android")
    }
}