package com.br.justcomposelabs.tutorial.google.compose.basics.stagesoflifecycle.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.br.justcomposelabs.tutorial.google.compose.basics.stagesoflifecycle.data.Datasource.desserts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {

    private val dessertUiState = MutableStateFlow(DessertUiState())
    val observeDessertUiState: StateFlow<DessertUiState> = dessertUiState.asStateFlow()

    fun onDessertClicked() {
        dessertUiState.update { uiState ->
            val dessertsSold = uiState.dessertsSold + 1
            val nextDessertIndex = determineDessertIndex(dessertsSold)
            uiState.copy(
                currentDessertIndex = nextDessertIndex,
                revenue = uiState.revenue + uiState.currentDessertPrice,
                dessertsSold = dessertsSold,
                currentDessertImageId = desserts[nextDessertIndex].imageId,
                currentDessertPrice = desserts[nextDessertIndex].price
            )
        }
    }

    private fun determineDessertIndex(dessertsSold: Int): Int {
        /*
            var dessertIndex = 0
            for (index in desserts.indices) {
                if (dessertsSold >= desserts[index].startProductionAmount) {
                    dessertIndex = index
                } else {
                    // The list of desserts is sorted by startProductionAmount. As you sell more
                    // desserts, you'll start producing more expensive desserts as determined by
                    // startProductionAmount. We know to break as soon as we see a dessert who's
                    // "startProductionAmount" is greater than the amount sold.
                    break
                }
            }
         */

        return desserts.indices.maxBy { idx ->
            desserts[idx].startProductionAmount >= dessertsSold
        }
    }
}


data class DessertUiState(
    val currentDessertIndex: Int = 0,
    val dessertsSold: Int = 0,
    val revenue: Int = 0,
    val currentDessertPrice: Int = desserts[currentDessertIndex].price,
    @param:DrawableRes val currentDessertImageId: Int = desserts[currentDessertIndex].imageId
)