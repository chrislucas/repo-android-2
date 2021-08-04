package com.br.cardhero.interactors

import com.br.cardhero.states.MainViewState
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface MainView : MvpView {

    fun getIntent(): Observable<Integer>

    fun render(state: MainViewState)
}