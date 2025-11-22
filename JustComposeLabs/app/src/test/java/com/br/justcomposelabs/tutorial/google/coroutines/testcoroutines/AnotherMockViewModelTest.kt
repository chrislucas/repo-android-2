package com.br.justcomposelabs.tutorial.google.coroutines.testcoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue


@OptIn(ExperimentalCoroutinesApi::class)
class AnotherMockViewModelTest {

    private val viewModel = AnotherMockViewModel()

    @Before
    fun setUp() {
        /*
            Para testar funcoes dessa viewmodel que lancam coroutines precisamos
            definir o Dispatcher que sera usado por ela. A viewmodel lanca
            a coroutine atraves do ViewModelScope, que usa por padrao o Main Dispatcher
            que faz parte do Android. Como nao podemos acessar esse dispatcher desde o teste
            usamos o StandardTestDispatcher()
         */
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `given a ViewModel when call register Then it state should change from false to true`() = runTest {
        assertFalse { viewModel.state.value }
        /*
            A funcao register lanca uma coroutines e finaliza imediatamente, o teste
            nao vai conseguir validar o estado da variavel state pq o metodo test nao espera a exeucao
            da coroutine

            - StandardTestDispatcher
                - Nos fornece os recursos para controlar a coroutine
                - podemos passar uma instancia de StandardTestDispatcher como argumento de runTest

         */
        viewModel.register()
        /*
            Essa fnucao executa a coroutine que ainda nao foi executada
            Se tivermos mais de uma coroutine lancada, precisamos chamar esse metodo
            a quantidade de vezes necessarias para executar todas elas
         */
        runCurrent()
        assertTrue { viewModel.state.value }
        /*
            O metodo register possui uma exeucao de um delay para simular uma operacao demorada,
            a funcao runCurrent executa a corouutine que foi lancada mais nao executada, mas
            nao faz com que o teste espere ou adiante a execucao da funcao delay.

            A funcao advanceUntilIdle adianta/pula essa execucao para nao ter que esperar para executar
            a assercao
         */
        advanceUntilIdle()
        assertFalse { viewModel.state.value }
    }


    @Test
    fun `given a ViewModel when call register and advanced the time Then it state should change from false to true`() = runTest {
        assertFalse { viewModel.state.value }
        /*
            A funcao register lanca uma coroutines e finaliza imediatamente, o teste
            nao vai conseguir validar o estado da variavel state pq o metodo test nao espera a exeucao
            da coroutine

            - StandardTestDispatcher
                - Nos fornece os recursos para controlar a coroutine
                - podemos passar uma instancia de StandardTestDispatcher como argumento de runTest

         */
        viewModel.register()
        /*
            Essa fnucao executa a coroutine que ainda nao foi executada
            Se tivermos mais de uma coroutine lancada, precisamos chamar esse metodo
            a quantidade de vezes necessarias para executar todas elas
         */
        runCurrent()
        assertTrue { viewModel.state.value }
        /*
            O metodo register possui uma exeucao de um delay para simular uma operacao demorada,
            a funcao runCurrent executa a corouutine que foi lancada mais nao executada, mas
            nao faz com que o teste espere ou adiante a execucao da funcao delay.

            A funcao advanceUntilIdle adianta/pula essa execucao para nao ter que esperar para executar
            a assercao
         */
        advanceTimeBy(1000L)
        assertFalse { viewModel.state.value }
    }

}