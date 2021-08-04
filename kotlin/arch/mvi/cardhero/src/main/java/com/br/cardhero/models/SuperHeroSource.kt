package com.br.cardhero.models

import io.reactivex.Observable


data class SuperHeroSource(private val superheroes: List<SuperHero>) {

    fun getObservableHero(index: Int) = Observable.just(superheroes[index])
}
