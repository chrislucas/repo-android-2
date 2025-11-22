package com.br.justcomposelabs.tutorial.architecture.mvi.labs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/*
    https://www.linkedin.com/posts/zain-ul-abdin-274787211_androiddevelopment-jetpackcompose-kotlin-ugcPost-7389517172487741441-zVMr?utm_source=share&utm_medium=member_desktop&rcm=ACoAAAucV48BgdbCBoMmXrArsYNH-OL_jFGhzfk
 */


interface UserEventListener<UserEvent> {
   fun onUserEvent(userEvent: UserEvent)
}



abstract class BaseViewModelExample<UserEvent, UIEvent>(
    private val userEventListener: UserEventListener<UserEvent>
) : ViewModel() {

    /*
        Uma discusso com a IA do porque
            - private val stateUiEvent: SharedFlow<UIEvent> = uiEvent nao é o suficiente para garantir que
            stateUiEvent seja somente um SharedFlow mesmo que uiEvent tenha sido definido como MutableSharedFlow<UIEvent

            - Em resumo a pergunta foi:
                - Why the definition uiEvent: SharedFlow<UIEvent> is not enough ?
                - Resposta
                    - The definition val uiEvent: SharedFlow<UIEvent> = uiEvent is not enough
                    because it doesn't prevent a malicious or buggy external component
                    from casting the property back to its original,
                    mutable type.

                   WHILE THE STATIC TYPE OF UIEVENT IS SHARED_FLOW, ITS DYNAMIC TYPE IS STILL MUTABLE_SHARED_FLOW
                   because that's the class of the underlying object it references.

        - A vulnerabilidade de definir diretamente
            - A vulnerabilidade surge da forma com que kotlin lida com o sistema de tipos
            e como os principios de OO funcionam

            - Type vs Objetos
                - Uma propriedade é um tipo construido em tempo de compilacao que é restringido
                como podemos interagir com ele num contexto. Entretando objetos armazenam em si
                em tempo de execucao sua completa capacidade

                - Runtime casting: Um desenvovledor pode escre codigo que em tempo de exeucao
                tenta realizar o cast de Sharedflow para MutableStateFlow. Se ele obter sucesso
                vai ter acesso ao metodo emit, um lugar onde somente deveria ter acesso a leitura
                passara a ter acesso a escrita

               - asSharedFlow
                    - Resulve isso criando uma nova instancia de SharedFlow, porem somente de leitura,
                    ReadonlySharedFlow que so implementa SharedFlow

                    - O novo objeto armazena a reference para uiEvent mas somente
                    expoe os metodos publicos de SharedFlow

                    - O novo objeto nao implementa MutableSharedFlow o que impede de realizar
                    o casting com sucesso

        - Fonte
            - StateFlow vs. SharedFlow: Android UI State Done Right
            - https://medium.com/@nirav.devtech/stateflow-vs-sharedflow-android-ui-state-done-right-44f74f293de9

      Exemplo de casting que poderia ser feito

      class MyViewModel : ViewModel() {
            // This is the private, mutable flow
            private val _uiEvent = MutableSharedFlow<UIEvent>()

            // This exposes the mutable flow with a read-only type.
            // This is the problematic part.
            val uiEvent: SharedFlow<UIEvent> = _uiEvent
        }

        // In a Fragment or other consumer
        fun observeEvents(viewModel: MyViewModel) {
            // This cast will succeed at runtime
            val mutableFlow = viewModel.uiEvent as? MutableSharedFlow<UIEvent>

            if (mutableFlow != null) {
                // We can now emit an event from the UI, bypassing the ViewModel's logic.
                mutableFlow.tryEmit(UIEvent.ShowToast("This is a big mistake"))
            }
        }


        https://www.google.com/search?q=what+is+the+difference+between+private+val+stateUiEvent%3A+SharedFlow%3CUIEvent%3E+%3D+uiEvent.asSharedFlow%28%29+and+private+val+stateUiEvent%3A+SharedFlow%3CUIEvent%3E+%3D+uiEvent&sca_esv=c5d665130b0b830b&rlz=1C5GCEA_enBR1109BR1109&udm=50&fbs=AIIjpHydJdUtNKrM02hj0s4nbm4yS-nYjdQche_l-llub_1h_KRuczP6XSL7TXZsEvGbGnfiz1YVoNq_z1xLpqtYMkLENdWspR2mFc9pgLFIIbPrvmub4-rhGt7ShRlJbqWxK_Y0DOBuE-2vS1loZShgv2SMvqmbzYV7fTwfQGYyGSNyGFJntVN9UTZcJPZDfGLJ1Ftjtzgk_Z2XWOJYSXwX-3Lcn39Ruw&aep=1&ntc=1&sa=X&ved=2ahUKEwiAm8qnh8yQAxUeCrkGHWpGJaUQ2J8OegQIERAE&biw=1512&bih=823&dpr=1&safe=active&ssui=on&mstk=AUtExfDUQ29lfEGxl_z1mozwPvV7fjaIBAgPpByYhjlwXQW0vdYBCYBJhfb1gQfNsWjmKJ3A0dvPfHwpK1sxMAkMkuhk320d7CrMjR-lkpx5BpwEYbdyKEGRo2Fdys7ynf_NNkKNtsNIUlthrmWDDsw7edFsDABbTBXf85t3TdumV5EDtTm2RwGSt0Surlh6wbEhYx-U3b7CQwKTcrc9dnq1Ba7fZ24acmKijEbNblxcbl8c7Hc9dl_4arWrlw&csuir=1
     */
    private val mutableSharedFlowUIEvent: MutableSharedFlow<UIEvent> = MutableSharedFlow()
    private val sharedFlowUiEvent: SharedFlow<UIEvent> = mutableSharedFlowUIEvent.asSharedFlow()

    fun tryEmitUiEvent(uiEvent: UIEvent) = mutableSharedFlowUIEvent.tryEmit(uiEvent)


    fun emitUserEvent(userEvent: UserEvent) {
        userEventListener.onUserEvent(userEvent)
    }

    fun viewModelIOScope(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(Dispatchers.IO, block = block)

    /*
        https://medium.com/@mortitech/sharein-vs-statein-in-kotlin-flows-when-to-use-each-1a19bd187553
        https://medium.com/androiddevelopers/things-to-know-about-flows-sharein-and-statein-operators-20e6ccb2bc74
     */
    fun <T> Flow<T>.toScopedCall(default: T) = flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5_000),
            initialValue = default
        )
}