package com.br.funwithviewmodel.tutorials.scopedbaseviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext

/*
    Por que usar um Custom Scope
    - Testing: Podemos injectar diferentes CoroutineScopes e Dispatachers (TestDispatcher)
    para facilitar os testes unitarios, ao inves de depender do Dispatchers.Main padrao.

    - Flexibilidade: Permite configurar o escopo de acordo com as necessidades especificas
    do ViewModel, como usar Dispatchers.IO para operacoes de I/O intensivas.

    - Isolamento: Cada ViewModel pode ter seu proprio escopo isolado, evitando interferencias
    entre diferentes ViewModels.

    - Controle de ciclo de vida: Podemos gerenciar o ciclo de vida das coroutines de forma mais precisa,
    garantindo que elas sejam canceladas corretamente quando o ViewModel for destruido

    - Reutilizacao de codigo: Podemos criar diferentes implementacoes de CoroutineScope
    para reutilizar em varios ViewModels, promovendo a consistencia no gerenciamento de coroutines

    - Custom Exception Handling: PPodemos definir CoroutineExceptionHandler para maniputar
    excecoes de maneira centralizad para todas as corotines lancadas dentro do escopo.

    - Specific Lifetime: Se precisarmos genreciar lifecycle de tarefas assincronas de forma
    mais granular, podemos usar o metodo onCleared para cancelar tarefas especificas
    sem afetar outras coroutines dentro do mesmo ViewModel.
 */
open class ScopedBaseViewModel(
     private val customScope: CoroutineScope  = CoroutineScope(Dispatchers.Main)
): ViewModel() {

    // SupervisorJob garante que uma falha em uma courouitne nao cancele o scope inteiro
    private val viewModelJob = SupervisorJob()

    // Escopo personalizado para as coroutines do ViewModel com Main Dispatcher
    protected val mCustomScope = customScope + viewModelJob // CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        // cancela o job quando o ViewModel e destruido
        viewModelJob.cancel()
    }
}

class SimpleScopedViewModel: ScopedBaseViewModel() {

     fun execution() {
        mCustomScope.launch {
            // Lógica da coroutine aqui
            withContext(Dispatchers.IO) {
                // Simula uma operacao de I/O
            }
        }
    }
}


/*
    Alternativas e melhores praticas

    Default ViewModelScope: O viewModelScope padrao ja genrecia automaticamente
    o metodo onCleared*() e eh recomendado para a maioria dos casos
 */
class SimpleViewModel: ViewModel() {

     fun execution() {
        // Usando ViewModelScope padrao
        viewModelScope.launch {
            // Lógica da coroutine aqui
            withContext(Dispatchers.IO) {
                // Simula uma operacao de I/O
            }
        }
    }
}

