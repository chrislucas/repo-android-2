package com.br.cardhero.states

import com.br.cardhero.models.SuperHero

sealed class PartialViewState {
    object Loading : PartialViewState()

    class GetSuperHeroList(val superhero: SuperHero) : PartialViewState()

    class Error(val error: Throwable) : PartialViewState()
}
