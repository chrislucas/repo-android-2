package com.br.justcomposelabs.tutorial.medium.funwithflowtype

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewModelScope
import com.br.justcomposelabs.tutorial.medium.funwithflowtype.ui.theme.JustComposeLabsTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/*
    https://medium.com/@shivayogih25/android-kotlin-stateflow-vs-flow-vs-sharedflow-vs-livedata-when-to-use-what-3521ebffcb5d
 */
class FunWithFlowTypesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OIObservedByStateFlowViewModel(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun OIObservedByStateFlowViewModel(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    stateFlowViewModel: StateFlowViewModel = StateFlowViewModel(),
    sharedFlowViewModel: SharedFlowViewModel = SharedFlowViewModel(),
    liveDataViewModel: LiveDataViewModel = LiveDataViewModel()
) {

    /*
        https://developer.android.com/develop/ui/compose/side-effects#disposableeffect
     */

    fun callbackLifecycle(event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                Log.d("OIObservedByStateFlowViewModel", "ON_CREATE")
            }

            Lifecycle.Event.ON_START -> {
                Log.d("OIObservedByStateFlowViewModel", "ON_START")
            }

            Lifecycle.Event.ON_RESUME -> {
                Log.d("OIObservedByStateFlowViewModel", "ON_RESUME")
            }

            Lifecycle.Event.ON_PAUSE -> {
                Log.d("OIObservedByStateFlowViewModel", "ON_PAUSE")
            }

            Lifecycle.Event.ON_STOP -> {
                Log.d("OIObservedByStateFlowViewModel", "ON_STOP")
            }

            Lifecycle.Event.ON_DESTROY -> {
                Log.d("OIObservedByStateFlowViewModel", "ON_DESTROY")
            }

            Lifecycle.Event.ON_ANY -> {
                Log.d("OIObservedByStateFlowViewModel", "ON_ANY")
            }
        }
    }

    /*
        https://developer.android.com/develop/ui/compose/side-effects#disposableeffect
        https://www.droidcon.com/2024/11/07/mastering-disposableeffect-in-jetpack-compose-managing-side-effects-effectively/
     */

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            callbackLifecycle(event)
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        UpdateStateFlowViewModel(stateFlowViewModel = stateFlowViewModel, modifier = modifier)
        Spacer(modifier = Modifier.padding(8.dp))
        UpdateSharedFlowViewModel(sharedFlowViewModel = sharedFlowViewModel, modifier = modifier)
        Spacer(modifier = Modifier.padding(8.dp))
        UpdateLiveDataViewModel(liveDataViewModel = liveDataViewModel, modifier = modifier)
    }
}


@Composable
fun UpdateStateFlowViewModel(
    modifier: Modifier = Modifier,
    stateFlowViewModel: StateFlowViewModel
) {

    val collectStateFlow by stateFlowViewModel.message.collectAsState()

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = collectStateFlow)
        Button(onClick = {
            val value = (collectStateFlow.toIntOrNull() ?: 0) + 1
            stateFlowViewModel.updateContent("$value")
        }) {
            Text("Update StateFlow")
        }
    }
}

class StateFlowViewModel() : ViewModel() {
    private val content = MutableStateFlow("Init StateFlowViewModel")
    val message: StateFlow<String> = content

    fun updateContent(newContent: String) {
        content.value = newContent
    }
}


@Composable
fun UpdateSharedFlowViewModel(
    modifier: Modifier,
    sharedFlowViewModel: SharedFlowViewModel

) {

    /*
        var stateSharedFlow by remember {
            mutableStateOf("Empty State Shared Flow")
        }
     */

    // outra maneira de iniciar uma mutableStateOf
    val stateSharedFlow = remember {
        mutableStateOf("Empty State Shared Flow")
    }

    LaunchedEffect(Unit) {
        sharedFlowViewModel.message.collectLatest { data ->
            stateSharedFlow.value = data
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stateSharedFlow.value)
        Button(onClick = {
            val value = (stateSharedFlow.value.toIntOrNull() ?: 0) + 1
            sharedFlowViewModel.updateContent("$value")
        }) {
            Text("Update SharedFlow")
        }
    }
}

class SharedFlowViewModel : ViewModel() {
    private val content = MutableSharedFlow<String>(replay = 1)

    init {
        viewModelScope.launch {
            content.emit("Init State Shared Flow")
        }
    }

    val message: SharedFlow<String> = content.asSharedFlow()

    fun updateContent(newContent: String) {
        viewModelScope.launch {
            content.emit(newContent)
        }
    }
}

@Composable
fun UpdateLiveDataViewModel(
    modifier: Modifier,
    liveDataViewModel: LiveDataViewModel
) {
    val observeLiveData by liveDataViewModel.message.observeAsState("Empty State LiveData")

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = observeLiveData)
        Button(onClick = {
            val value = (observeLiveData.toIntOrNull() ?: 0) + 1
            liveDataViewModel.updateContent("$value")
        }) {
            Text("Update Livedata")
        }
    }
}

class LiveDataViewModel : ViewModel() {
    private val content = MutableLiveData("Init State LiveData")
    val message: LiveData<String> = content

    fun updateContent(newContent: String) {
        content.value = newContent
    }
}


@Preview(showBackground = true)
@Composable
fun OIObservedByStateFlowViewModelPreview() {
    JustComposeLabsTheme {
        OIObservedByStateFlowViewModel(Modifier.fillMaxWidth())
    }
}