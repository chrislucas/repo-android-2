package com.br.cardhero.presenter

import com.br.cardhero.interactors.MainView
import com.br.cardhero.models.SuperHeroSource
import com.br.cardhero.states.MainViewState
import com.br.cardhero.states.PartialViewState
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter

class MainPresenter(val datasource: SuperHeroSource, val initialViewState: MainViewState) :
    MviBasePresenter<MainView, MainViewState>(initialViewState) {

    override fun bindIntents() {

    }


    private fun viewStateReducer(
        oldState: MainViewState,
        changedState: PartialViewState
    ): MainViewState {

        return when (changedState) {
            is PartialViewState.Loading -> {
                MainViewState(
                    isLoading = true,
                    isCardShown = false,
                    superhero = oldState.superhero,
                    error = oldState.error
                )
            }

            is PartialViewState.GetSuperHeroList -> {
                MainViewState(
                    isLoading = false,
                    isCardShown = true,
                    superhero = changedState.superhero,
                    error = null
                )
            }

            else -> {
                MainViewState(
                    isLoading = false,
                    isCardShown = false,
                    superhero = oldState.superhero,
                    error = (changedState as PartialViewState.Error).error

                )
            }
        }
    }
}