package com.br.start.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.start.repositories.TitleRepository
import com.br.start.utils.buildViewModel

class StartViewModel(private val repository: TitleRepository) : ViewModel() {

    companion object {

        val FACTORY = buildViewModel(::StartViewModel)
    }


    /**
     * Objeto que representa o estado da Snackbar na camada de visualizacao
     * */
    private val mStateSnackBar = MutableLiveData<String?>()

    val stateSnackBar: LiveData<String?>
        get() = mStateSnackBar


    private val mStateLoadingSpinner = MutableLiveData(false)

    val stateLoadingSpinner: LiveData<Boolean>
        get() = mStateLoadingSpinner


    val title = repository.title

    /**
     * Contador de toques na tela
     * */
    private var mTapCount = 0


    private val mTapsCounterState =
        MutableLiveData("$mTapCount ${if (mTapCount > 1) "taps" else "tap"}")
}