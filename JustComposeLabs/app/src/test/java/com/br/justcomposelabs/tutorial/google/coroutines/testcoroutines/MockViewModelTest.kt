package com.br.justcomposelabs.tutorial.google.coroutines.testcoroutines

import com.br.justcomposelabs.MainDispatcherRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

/*
    https://developer.android.com/kotlin/coroutines/test?hl=pt-br
    https://developer.android.com/kotlin/flow/test?hl=pt-br

 */

@ExperimentalCoroutinesApi
class MockViewModelTest {

    private lateinit var viewModel: MockViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = MockViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given a ViewModel when call fetchContent Then it state should change from loading to loaded in 1s`() =
        runTest {
            assertEquals("", viewModel.data.value)
            viewModel.fetchContent()
            runCurrent()
            assertEquals("loading ...", viewModel.data.value)
            advanceUntilIdle()
            assertEquals("loaded", viewModel.data.value)
        }
}