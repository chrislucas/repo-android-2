package com.br.cardhero.states

import com.br.cardhero.models.SuperHero

class MainViewState(
    val isLoading: Boolean,
    val isCardShown: Boolean,
    val superhero: SuperHero,
    val error: Throwable?
)