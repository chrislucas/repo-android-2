package com.br.justcomposelabs.tutorial.google.compose.state

import android.R.attr.name
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/*
    https://developer.android.com/develop/ui/compose/state
    - Estado num app é qualquer valor que pode mudar conforme o tempo. Essa uma definicao
    ampla que abrange desde uma variavel  a um banco de dados
 */


@Preview(showBackground = true, showSystemUi = true, name = "StateTextField")
@Composable
fun StateTextField() {

    /*
        // an observable tupe in compose
        Se utilizarmos somente o mutableStateOf toda vez que ocorrer uma recomposicao
        o estado inicial da variavel name sera uma String vazia ou o valor inicial
        de mutableStateOf

        Pergunta: Existe diferenca entre colocar o  mutableStateOf dentro ou fora
        da funcao composable  ?

        https://stackoverflow.com/questions/66169601/what-is-the-difference-between-remember-and-mutablestate-in-android-jetpack

        In other words the deference between this line

        val text = remember{ mutableStateOf("") }
        and this

        val text = remember{ "" }
        and this also

        val text = mutableStateOf("")

        - remember: composable function que pode ser usado como uma funcao de cache para
        operacoes custosas

            - val text = remember{ "" }
                - a variavel text é imutavel, para torna-la mutavel precisamos fazer o seguinte
                - val text: MutableState<String>  = remember{ "" }
            -  var name: String by (rememberSaveable ou remember) { mutableStateOf(" ") }
                - Sintax Sugger para fazer um unwrapping de MutableState<String>




     */
    // var name: String by remember { mutableStateOf("") }

    /*
       remember nos ajuda a manter o estado entre as recomposicoes, porem nao
       resolve o problema entre mudancas de configuracao de tela. Para isso
       usamos o rememberSaveable.

       rememberSaveable é capaz de salvar automaticamente qualquer valor que é
       possivel de ser armazenado em um Bundle.
     */
    var name: String by rememberSaveable { mutableStateOf(" ") }

    Column(modifier = Modifier.padding(16.dp)) {

        if (name.isNotBlank()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        OutlinedTextField(
            value = name,
            onValueChange = {},
            label = { Text("label") }
        )
    }
}


@Preview(showBackground = true, showSystemUi = true, name = "StateTextFieldRemember")
@Composable
fun StateTextFieldRemember() {

    /**
     * @see androidx.compose.runtime.MutableState
     * Essa desistruturacao abaixo vem da interface MutableSate que tem
     *  - Um atributo mutavel value  T
     *  - dois medotos operators
     *      - um para retornar o valor
     *      - e outro uma funcao que recebe o tipo T como argumento
     *       e atualiza o atributo generico value T
     */
    val (name, setName) = remember { mutableStateOf(" ") }
    Column(modifier = Modifier.padding(16.dp)) {
        if (name.isNotBlank()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        OutlinedTextField(
            value = name,
            onValueChange = { setName(it) },
            label = { Text("label") }
        )
    }
}


@Preview(showBackground = true, showSystemUi = true, name = "MutableStateTextFieldRemember")
@Composable
fun MutableStateTextFieldRemember() {
    val state: MutableState<String> = remember { mutableStateOf(" ") }
    Column(modifier = Modifier.padding(16.dp)) {
        if (state.value.isNotBlank()) {
            Text(
                text = "Hello, ${state.value}!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        OutlinedTextField(
            value = state.value,
            onValueChange = { it -> state.value = it },
            label = { Text("label") }
        )
    }
}


@Preview(showBackground = true, showSystemUi = true, name = "MutableStateByDelegateTextFieldRemember")
@Composable
fun MutableStateByDelegateTextFieldRemember() {
    var name: String by remember { mutableStateOf(" ") }
    Column(modifier = Modifier.padding(16.dp)) {
        if (name.isNotBlank()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        OutlinedTextField(
            value = name,
            onValueChange = { it -> name = it },
            label = { Text("label") }
        )
    }
}


@Composable
@Preview(showSystemUi = true, name = "HelloScreen|HelloContent")
fun HelloScreen() {
    var name: String by rememberSaveable { mutableStateOf(" ") }
    HelloContent(name) { newName ->
        name = newName
    }
}

/*
    A ideia de usar um view model
 */
class HelloStateFlowViewModel : ViewModel() {
    private val _name: MutableStateFlow<String> = MutableStateFlow("")
    val name: StateFlow<String> = _name

    fun onNameChange(newName: String) {
        _name.value = newName
    }
}


/*
    A viewmodel armazena e expoem o estado da variavel name
    atraves de um live data.
 */
class HelloLiveDataViewModel : ViewModel() {
    private val _name: MutableLiveData<String> = MutableLiveData("")
    val name: LiveData<String> = _name

    /*
        Hoisting (içar, levantar): trazemos o gerenciador do estado da variavel name
        para "cima" na viewmodel, isto é tirando da funcao composable e delegando
        a responsabilidade de gerenciamento para um elemento externo a ela.

        A view model passa a ser responsavel pela atualizacao da UI e torna-se
        a "unica fonte de verdade" ou a chamada single source of truth da UI.
        Essa atualizacao pode vir de diversos pontos, como um DB ou uma requisicao
        de rede, mas o unico ponto de atualizacao que a UI se serve é aqui.
     */
    fun onNameChange(newName: String) {
        _name.value = newName
    }
}

@Composable
@Preview(showSystemUi = true, name = "HelloStateFlowViewModel")
fun HelloScreen(helloStateFlowViewModel: HelloStateFlowViewModel = viewModel()) {
    val name by helloStateFlowViewModel.name.collectAsState()
    HelloContent(name, helloStateFlowViewModel::onNameChange)
}

@Composable
@Preview(showSystemUi = true, name = "HelloLiveDataViewModel")
fun HelloScreen(helloLiveDataViewModel: HelloLiveDataViewModel = viewModel()) {
    /*
        Toda vez que a que o estado do LiveData mudar aciona-se a recomposition.
        Como a Viewmodel sobrevive a mudancas de confiuracao, nao precisamos
        fazer mais nada para manter o estado da UI, internamente
        a funcao observeAsState vai armazenar o estado da variavel name, garantido
        o armazenamento do estado durante a recomposicao.

     */
    val name: String by helloLiveDataViewModel.name.observeAsState("")
    HelloContent(name, helloLiveDataViewModel::onNameChange)
}

@Composable
private fun HelloContent(name: String = "", onNameChange: (String) -> Unit) {
    /*
        Manter o gerenciamento de estado dentro da funcao composable faz com que ela
        seja dificil de ser reutilizada, dificil de testa-la unitariamente.e a mantem
        altamente acoplada com a maneira que seu estado é armazenado.

        Um stateless composable é uma funcao que nao armazena nenhum estado. Para realizar
        isso usamos um conceito chamado State hoisting

        State hoisting é um padrao de programacao onde passamos o estado da funcao composable para
        aquele que a chama
     */

    Column(modifier = Modifier.padding(16.dp)) {
        if (name.isNotBlank()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("label") }
        )
    }
}


/*
    Resumo
    # Persist state across re-composition: remember
    # Persist state across configuration change: rememberSaveable

    # hoist the state for re-usability and testability
        - remover o controle de estado da funcao composable
        - passar por parametro ou encapsular numa ViewModel
        - Usar uma ViewModel permite criar uma unica fonte da verdade
        enquanto encapsula a logica de atualizacao.
 */