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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.justcomposelabs.tutorial.google.compose.recompositionhighlighter.recomposeHighlighter
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


@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "MutableStateByDelegateTextFieldRemember"
)
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
    /**
     * @see HelloScreenFlow
     * usando collectAsStateWithLifecycle
     * - Coleta o ultimo valor emitido pelo State Flow de uma maneira
     * vinculada|ciente do cicloe de vida da Activity
     *
     * - State Flow lifecycle aware
     *   - State flow nao é lifecycle aware como um LiveData. Enquanto
     *   o livedata gerencia seus observadores baseado no lifecycle dos components
     *   Android, pausando observacao quando o componente esta em background e
     *   retornando quando esta em foreground, StateFlow requer um gerenciamento explicito
     *   para obter ciencia do lifecycle do componente que esta atuando
     *
     *   - Para fazer o State Flow lifecycle aware em jetpack compose precisamos
     *   usar a composable function collectAsStateWithLifecycle
     *
     *      - Lifecycle-aware collection
     *           - Essa funcao coleta valroes dum Flow (inclua StateFlow) e representa
     *           o ultimo valor emitido como um Compose State
     *
     *      - Automatic management
     *          - Automaticamente inicia a coleta do flow quando o LifecycleOwner associado
     *          alcanca o estado ativo minimo, por padrao é o Lifecycle.State.STARTED,
     *          e para a coleta quando o lifecycle cai abaixo desse estado
     *
     **@see collectAsStateWithLifecycle
     * @see androidx.lifecycle.Lifecycle.State.STARTED
     *
     * fun <T> StateFlow<T>.collectAsStateWithLifecycle(
     *     lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
     *     minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
     *     context: CoroutineContext = EmptyCoroutineContext
     * )
     * A alternativa para funcoes nao composable
     *
     * lifecycleScope.launch {
     *     repeatOnLifecycle(Lifecycle.State.STARTED) {
     *         myStateFlow.collect { value ->
     *             // Process the emitted value
     *         }
     *     }
     * }
     *
     * 0 codigo acima garante que a coleta do State Flow esta alinhada com o ciclo de vida do
     * componente android que a iniciou.
     *
     * - Compose automaticamente aplica recomposicao ao ler objetos States. Se usarmos
     * outro tipo de observador como LiveData em Compose, devemos converter
     * pra State antes de ler.
     *
     *
     * - Stateful versus stateless
     *  - Composables que armazenam estados VS Composables que nao armazenam
     *      - Uma composable que usa remember function para armazenar um objeto
     *      cria um estado interno, portanto é uma Stateful Composable
     *
     *      - Uma Stateless composable é uma composable que nao armazena estado
     *         - Para resolver essa questao existe uma tecnica denominada
     *         State Hoisting
     *
     *         - A vantagem de usar State Hoisting está no reaproveitamento
     *         da funcao Composable, ja que o estado é passado para ela, portanto
     *         gerenciado por quem a usa, e isso facilita testa-la também
     *
     *          - Uma pratica é expor versoes Stateful e Stateless do mesmo Composable
     *
     *   - State Hoisting
     *      - Uma tecnica para mover o Estado para uma funcao composable A acima da funcao
     *      B que necessita desse estado, fazendo da funcao B Stateless
     *
     *          - A forma de implementacao dessa tecnica/padrao é a seguinte
     *              uma funcao F (value: T, onValueChange: (T) -> Unit)
     *              - primeiro argumento é o valor atual a ser usado no estado
     *              - a funcao lambda serve para ser chamada quando o valor mudar e precisamos
     *              atualiza o valor corrente
     *
     *   - Ao delegar a responsabilidade de gerenciar o estado para um elemento externo a funcao
     *   cria algumas propriedades interessantes
     *      - Single source of truth
     *      - Encapsulated
     *      - Shareable: Estados que sao elevados a funcoes acima da Stateless Composable podem
     *      ser compartilhados com mais composables. Se houver a necessidade e ler o Estado
     *      de algo antes de chegar na composable que vai usa-lo, essa tecnica vai permitir isso
     *
     *      - Interceptable: Quem chama a Stateless Composable pode interceptar seu estado
     *      e decidir fazer algo com ela
     *
     *      - Declouped: Pode armazenar o estado de uma Stateless Composable em qualquer
     *      lugar, por exemplo numa ViewModel
     *
     *
     */
    val name by helloStateFlowViewModel.name.collectAsState()
    HelloContent(name, helloStateFlowViewModel::onNameChange)


    /*
        UDF - Unidirectional Data Flow
            - O padrao state hoisting gera um padrao de fluxo de dados, onde
            A funcao composable A que controla o estado envia o Estado e a funcao composable B,
            que usa o estado e envia o evento de mudanca para funcao A para executar a evento
            de atualizacao.

            - A -> State -> B
            - B -> Event -> A

           - Ao Seguir o UDF, podemos desacoplar composable que mostram estados numa UI
           des partes do app que armazenam esse estado e os gerenciam.

         - Maneiras de descobrir onde o estado deve estar armazenado
            - O estado deve ser enviado ao menos para a funcoa composable na estrutura
            que seja o "Menor parente comum ( lowest common parent)"
            a todas as funcoes composables que usam esse estado

            - O estado deve ser levado para o ponto mais alto onde ele pode ser modificado

            - Se 2 estados mudam em respota do mesmo evento, ambos devem ser elevados juntos
     */
}

@Composable
@Preview(showSystemUi = true, name = "HelloLiveDataViewModel")
fun HelloScreen(helloLiveDataViewModel: HelloLiveDataViewModel = viewModel()) {
    /*
        Toda vez que a que o estado do LiveData mudar aciona-se a recomposition.
        Como a Viewmodel sobrevive a mudancas de configuracao, nao precisamos
        fazer mais nada para manter o estado da UI, internamente
        a funcao observeAsState vai armazenar o estado da variavel 'name', garantido
        o armazenamento do estado durante a recomposicao.
     */
    val name: String by helloLiveDataViewModel.name.observeAsState("")
    HelloContent(name, helloLiveDataViewModel::onNameChange)
}

@Composable
internal fun HelloContent(name: String = "", onNameChange: (String) -> Unit) {
    /*
        Manter o gerenciamento de estado dentro da funcao composable faz com que ela
        seja dificil de ser reutilizada, dificil de testa-la unitariamente.e a mantem
        altamente acoplada com a maneira que seu estado é armazenado.

        Um stateless composable é uma funcao que nao armazena nenhum estado. Para realizar
        isso usamos um conceito chamado State hoisting

        State hoisting é um padrao de programacao onde passamos o estado da funcao composable para
        aquele que a chama
     */

    /**
     * @see recomposeHighlighter
     * TODO testar essa funcao
     */
    Column(
        modifier = Modifier
            .padding(16.dp)
            .recomposeHighlighter(false)
    ) {
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

    # hoist the state for re-usability and testability (passar a responsabilidade de gerenciar
    o estado para um elemento externo a funcao composable)
        - remover o controle de estado da funcao composable
        - passar por parametro ou encapsular numa ViewModel
        - Usar uma ViewModel permite criar uma unica fonte da verdade
        enquanto encapsula a logica de atualizacao.
 */