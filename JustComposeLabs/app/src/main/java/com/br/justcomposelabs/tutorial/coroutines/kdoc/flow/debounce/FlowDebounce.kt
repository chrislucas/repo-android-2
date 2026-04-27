package com.br.justcomposelabs.tutorial.coroutines.kdoc.flow.debounce

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.br.justcomposelabs.tutorial.medium.sideeffects.deepdivesnapshotflow.DistinctMessage
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import timber.log.Timber

/*
    https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/debounce.html
    - Retorna
 */

class FlowMessageDebounceViewModel(
    limit: Long,
) : ViewModel() {
    /*
        distinctUntilChanged
        https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/distinct-until-changed.html

        debounce
        https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/debounce.html
     */
    @OptIn(FlowPreview::class)
    val distinctIntUntilChange =
        flow {
            while (true) {
                val number = (1..10).random()
                val timeMillis = (1000..2000).random().toLong()
                val message = "Emitting number: $number | Limit: ${limit}ms | TimeMillis: ${timeMillis}ms"
                Timber.tag("IntViewModelDebounce").d(message)
                delay(timeMillis)
                emit(message)
            }
        }.debounce(limit)

    companion object {
        /*
            Pass custom parameters as CreationExtras
            https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-factories#creationextras_custom
         */
        val KEY_DEBOUNCE = object : CreationExtras.Key<Long> {}

        private val DEFAULT_DEBOUNCE_LIMIT = 1000L
        val FACTORY =
            viewModelFactory {
                initializer {
                    val limit = this[KEY_DEBOUNCE] ?: DEFAULT_DEBOUNCE_LIMIT
                    FlowMessageDebounceViewModel(limit)
                }
            }
    }
}

@Composable
fun DebounceScreen(
    viewModel: FlowMessageDebounceViewModel =
        viewModel(
            factory = FlowMessageDebounceViewModel.FACTORY,
            extras = MutableCreationExtras().apply { this[FlowMessageDebounceViewModel.KEY_DEBOUNCE] = 1500L },
        ),
) {
    val distinctIntUntilChange by viewModel.distinctIntUntilChange.collectAsState(initial = "*")
    DebounceContent(distinctIntUntilChange = distinctIntUntilChange)
}

@Composable
fun DebounceContent(distinctIntUntilChange: String) {
    DistinctMessage(distinctIntUntilChange = "DebounceContent recomposed with value: $distinctIntUntilChange")
}

@Preview(showBackground = true)
@Composable
fun DebounceScreenPreview() {
    JustComposeLabsTheme {
        DebounceScreen()
    }
}
