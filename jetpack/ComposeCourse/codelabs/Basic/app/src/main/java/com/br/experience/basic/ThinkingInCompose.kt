package com.br.experience.basic

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.br.experience.basic.ui.theme.BasicTheme

/**
 * https://developer.android.com/jetpack/compose/mental-model#recomposition
 *
 * Compose is a modern DECLARATIVE UI Toolkit for Android.
 *
 *  - Copose prove uma api declarativ que permiter renderizar componentes UI "sem imperativamente
 *  modificar componentes visuais"
 *
 *  Declarative Programming Paradignm
 *  https://developer.android.com/jetpack/compose/mental-model#paradigm
 *  - A hierarquia de uma view no android tem sido representado como uma Arvore de widgets UI, conforme
 *  o estado do app muda, essa estrutura hierarquica precisa ser atualizada para mostrar o estado atual
 *  dos dados.
 *
 *  - a forma mais usual de modificar um componente nessa estrutura em arvore é achar o componente
 *  atraves do metodo findViewById (basicamente navegar nessa estrutura)
 *
 *  - Manipular views manualmente aumenta a probabilidade de cometer erros
 *
 *  Caracteristicas de funcoes Composables
 *
 *  - Funcoes rapidas, idempotentes, e livres de side-effects
 *      - A funcao se comporta da mesma maneira sendo chamada multiplas vezes com o mesmo argumento. Nao
 *      usa valores globais ou chamaadas a funcao ranom()
 *      - A funcao descreve a UI sem nenhum side-effects, tais como modificar propriedades ou variaveis
 *      globais
 *
 *      Toda a funcao composable deve ser escrita com essas propriedades
 *
 * The declarative paradigm shift
 * https://developer.android.com/jetpack/compose/mental-model#paradigm
 *
 *      - Com os toolkits de UI imperativos orientados a objetos, inicializamos a UI criando uma
 *          arovre de widgets. Geralmente fazemos isso "inflando" um arquivo XML.
 *
 *      - Cada widget gerencia o seu proprio estado, expondo getters e setters permitidindo a alteracao
 *      desse estado com quem precisar interagir com o widget
 *
 *      - Pela abordagem declarativa do Compose, widgets sao relativamente "stateless", nao expoe getters
 *      ou setters. Widgets nao sao expostos como objetos.
 *
 *          _ Atualizamos um widget chamando a funcao composable passando um argumento diferente
 *          - Isso torna facil prover um estado consistente para um padrao arquitetural como o ViewModel
 *          - A funcao composable fica responsavel por transformar o estado atual da aplicacao numa
 *          UI toda vez que um objeto/dado observado for atualizado
 *
 *
 *
 *  Recomposition: https://developer.android.com/jetpack/compose/mental-model#recomposition
 *      - Quando o usuario interage com um componente da UI e esse dispara um evento, esse evento
 *      notifica o app que pode mudar o estado dele. Quando esse estado eh alterado a funcao
 *      composable eh chamada novamente com o novo dado, causando o redesenho da UI. Esse processo
 *      eh chamado de recomposition
 *
 */
class ThinkingInCompose : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicTheme {
                Column {
                    ClickCounter(1)
                    val ctx = LocalContext.current
                    ClickCounter(1) {
                        Toast.makeText(ctx, "Button 2 Clicked", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    @Composable
    fun Default() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Text(text = "Test")
        }
    }

    @Composable
    fun ClickCounter(counter: Int) {
        val stateCounter = remember { mutableStateOf(counter) }
        val ctx = LocalContext.current
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.background
        ) {
            Button(onClick = {
                Toast.makeText(ctx, "Clicked", Toast.LENGTH_SHORT).show()
                stateCounter.value += 1
            }) {
                Text(text = "${stateCounter.value} ${if (stateCounter.value > 1) "clicks" else "click"}")
            }
        }
    }

    @Composable
    fun ClickCounter(counter: Int, fn: () -> Unit) {
        val stateCounter = remember { mutableStateOf(counter) }
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.background
        ) {
            Button(onClick = fn) {
                Text(text = "${stateCounter.value} ${if (stateCounter.value > 1) "clicks" else "click"}")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewClickCounter() {
        BasicTheme {
            Column {
                ClickCounter(1)
                ClickCounter(1) {

                }
            }
        }
    }
}

