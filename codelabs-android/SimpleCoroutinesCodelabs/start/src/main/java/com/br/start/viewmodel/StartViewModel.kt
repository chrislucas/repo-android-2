package com.br.start.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.start.repositories.TitleRepository
import com.br.start.utils.buildViewModel
import com.br.start.utils.executorService

class StartViewModel(private val repository: TitleRepository) : ViewModel() {

    companion object {

        val FACTORY = buildViewModel(::StartViewModel)
    }

    /**
     * Objeto que representa o estado da Snackbar na camada de visualizacao
     * */
    private val mObservableStateSnackBar = MutableLiveData<String?>()

    val observableStateSnackBar: LiveData<String?>
        get() = mObservableStateSnackBar


    val mObservableTitle: LiveData<String?> = repository.title

    private val mObservableStateLoadingSpinner = MutableLiveData(false)

    val observableSpinner: LiveData<Boolean>
        get() = mObservableStateLoadingSpinner

    /**
     * Contador de toques na tela
     * */
    private var mTapCount = 0

    private fun provideTapText() ="$mTapCount ${if (mTapCount > 1) "taps" else "tap"}"

    private val mObservableTapsCountState =
        MutableLiveData(provideTapText())

    val observableTapsCountState: LiveData<String>
        get() = mObservableTapsCountState

    fun onMainViewCLicked() {
        refreshTitle()
        updateTaps()
    }


    private fun refreshTitle() {
        mObservableStateLoadingSpinner.value = true
    }

    private fun updateTaps() {
        mTapCount++
        executorService(2).submit {
            Thread.sleep(1000)
            mObservableTapsCountState.postValue(provideTapText())
        }
    }

    fun onSnackBarShown() {
        /**
         * Difference between setValue and postValue
         * https://stackoverflow.com/questions/51299641/difference-of-setvalue-postvalue-in-mutablelivedata#
         *
         * Segundo a resposta mais votada e o conteudo tirado da doc android:
         *
         * setValue: https://developer.android.com/reference/androidx/lifecycle/LiveData#setValue(T)
         *
         * postValue: https://developer.android.com/reference/androidx/lifecycle/LiveData#postValue(T)
         * */
        mObservableStateSnackBar.value = null
    }
}