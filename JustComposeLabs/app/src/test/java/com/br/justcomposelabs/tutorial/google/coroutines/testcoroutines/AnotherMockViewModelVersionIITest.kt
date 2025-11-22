package com.br.justcomposelabs.tutorial.google.coroutines.testcoroutines

import com.br.justcomposelabs.MainDispatcherRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class AnotherMockViewModelVersionIITest {

    @get:Rule
    private val mainCoroutineRule = MainDispatcherRule()

    private lateinit var viewModel: AnotherMockViewModelVersionII

    @Before
    fun setUp() {
        viewModel = AnotherMockViewModelVersionII()
        Dispatchers.setMain(mainCoroutineRule.testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test`() = runTest {
        assertEquals("", viewModel.data.value)
        viewModel.fetchData()
        runCurrent()
        assertEquals("loading...", viewModel.data.value)
        advanceUntilIdle()
        assertEquals("loaded.", viewModel.data.value)
    }
}