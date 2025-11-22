package com.br.justcomposelabs.tutorial.architecture.mvi.ai.examplei

import kotlinx.coroutines.delay

/*
    Model View Intent example android without hilt or dagger
    https://www.google.com/search?q=Model+View+Intent+example+android+without+hilt+or+dagger&rlz=1C5GCEA_enBR1109BR1109&oq=Model+View+Intent+example+android+without+hilt+or+dagger&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIHCAEQIRigATIHCAIQIRigATIHCAMQIRigAdIBCTEyNzYwajBqNKgCALACAQ&sourceid=chrome&ie=UTF-8&safe=active&ssui=on
 */

interface RepositoryPlainText {
    suspend fun fetch(): List<String>
}

class MockRepository: RepositoryPlainText {
    override suspend fun fetch(): List<String> {
        delay(1000L)
        return listOf("item1", "item2", "item3")
    }
}


sealed class MainIntent {
    object LoadData: MainIntent()
    data class ItemClocked(val itemId: String): MainIntent()
}

/*
    ViewState: Representa UI State da tela
 */

data class MainViewState(
    val isLoading: Boolean = false,
    val data: List<String> = emptyList(),
    val error: String? = null
)


class MainViewModel()