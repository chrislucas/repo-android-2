package com.br.composerecomposition.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.composerecomposition.tutorials.utils.currentHour
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class ClockViewModelStateFlow : ViewModel() {
    private val mutableStateClock = MutableStateFlow(currentHour())

    val stateClock: StateFlow<String> = mutableStateClock.asStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                // thread safe
                mutableStateClock.update { currentHour() }
                delay(1000)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClockStateFlow(viewModel: ClockViewModelStateFlow = viewModel()) {
    val currentHour by viewModel.stateClock.collectAsState()
    BoxContainer(
        Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(BorderStroke(2.dp, Color.Red))
    ) {
        Text("ClockStateFlow", Modifier.align(Alignment.TopCenter))
        Text(
            currentHour,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}


@Composable
fun BoxContainer(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        content()
    }

    LifecycleAwareComponent()
}