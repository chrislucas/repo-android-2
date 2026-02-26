package com.br.mviarchtecturepatternii

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.mviarchtecturepatternii.CounterIntent.*
import com.br.mviarchtecturepatternii.ui.theme.MVIArchtecturePatternIITheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*
    https://jamshidbekboynazarov.medium.com/mvi-architecture-pattern-in-android-with-jetpack-compose-0fd61f9c67fd

    MVI - Model View Intent.
        - Intent (Action|Event): Representa a acao do usuario ou sistema
        - Model (State): Uma data class imutavel que representa os possiveis
        estados da interface de usuário (UI) num dado momento
        - View: UI que renderiza o estado e emite eventos

    - o fluxo é unidirecional e ciclico
        - uma view dispara uma Intencao (Acao/Evento/Intent)
        - um reducer processa a a intencao e o estado atual produz um
        novo estado imutável
        - A view renderiza o novo estado
        - Eventos do tipo one-time effect pode ser emitidos separadamente
            - (navigation, toast)
        - Isso permite um loop preditivel, similar a arquiteturas como
        redux ou elm
    - Beneficios
        - Preditividade: Mudancas de estados sao explicitas e rastreáveis
        - Testabilidade: Reducers sao funcoes puras
        - Time-Travel Debudding: Fácil de emitir logs/registros de estados

    - Comparacao MVI e MVVM com UDF (undirect data flow)
        - MVVM com UDF é parecido com um lightweight MVI
        - State Representation
            - MVVM + UDF:
                - Single immutable UiState (data class)
            - Strict MVI:
                - Single Immutable State (often sealed class)

        - Events
            - MVVM + UDF:
                - Functions ou sealed class events
            - Strict MVI:
                 - Sealed Intent/Action class

        - Side Effects
            - MVVM + UDF:
                - LaunchedEffect or direct in ViewModel
            - Strict MVI:
                - Separate Effect flow/channel

        - State Updates
            - MVVM + UDF:
                - MutableStateFlow.update {}
            - Strict MVI:
                - Pure reducer: (State, Intent) -> State

        - Boilerplates
            - MVVM + UDF:
                - low
            - Strict MVI:
                - Alto mas mitigado por bibliotecas

       - Scalability
            - MVVM + UDF:
                - Alto para a maioria dos apps
            - Strict MVI:
                - Excelente para UIs reativas complexas

       - Official Support
            - MVVM + UDF:
                - Altamente recomendado
            - Strict MVI:
                - Dirigido pela comunidade
        - Muitos tratam MVVM + UDF como um MVI simplificado. MVI estrito adiciona reducers
        e effects para uma melhor predição
    MVVM + UDF:
        - https://developer.android.com/develop/ui/compose/architecture
 */

data class CounterState(
    val count: Int = 0,
    val isLoading: Boolean = false
)

sealed interface CounterIntent {
    data object Increment : CounterIntent
    data object Decrement : CounterIntent

    data object LoadData: CounterIntent
}

class CounterViewModel : ViewModel() {
    private val mutableState = MutableStateFlow(CounterState())
    val state: StateFlow<CounterState> = mutableState.asStateFlow()

    fun dispatch(intent: CounterIntent) {
        when (intent) {
            Increment -> {
                /*
                    Thread-safe update
                 */
                mutableState.update { state ->
                    state.copy(count = state.count + 1)
                }
            }

            Decrement -> {
                mutableState.update { state ->
                    state.copy(count = state.count - 1)
                }
            }

            LoadData -> {
                // nothing
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVIArchtecturePatternIITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/*
    UDF: Unidirectional Data Flow é o padrao de gerenciamento de estado de UI in
    Compose.

    Principios
        - O padrao UDF garante que a UI é uma funcao do estado atual e que todas
        as mudancas de estado mudam num local centralizado (The Source of Truth)

      - o ciclo do UDF
        - Event : Um evento é gerado na UI e passado para um state holder
        - Update State: O State Holder processa o evento, execuca a logica e atualiza internamente
        o estado
        - Display State: O novo estado flow para UI, os composables que precisam atualizar seram
        recompostos (recomposition/redraw)


      key components
      - UI State: O dado dita como a UI vai ser e se comportar a tod0 momento. A estrutura
      de dados deve ser minima e imutavel sempre que possível

      - State Holder: A classe responsabel por gerenciar o estado e manipular a logica
      de atualizacao
            - 

 */


/*
    StateFlow vs MutableStateOf
 */




@Composable
private fun CounterScreen(
    modifier: Modifier = Modifier,
    /*
        Criar uma instancia da viewmModel sem recria-la a cada recomposicao
     */
    viewModel: CounterViewModel = viewModel()
) {
    /*
        Other supported types of state
        https://developer.android.com/develop/ui/compose/state#use-other-types-of-state-in-jetpack-compose


     */
    val state by viewModel.state.collectAsStateWithLifecycle()//collectAsState() //collectAsStateWithLifecycle()

    Column(modifier) {
        Text("Count: ${state.count}")
        Row {
            Button(onClick = { viewModel.dispatch(Increment) }) {
                Text("Increment")
            }
            Button(onClick = { viewModel.dispatch(Decrement) }) {
                Text("Decrement")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    MVIArchtecturePatternIITheme {
        CounterScreen()
    }
}