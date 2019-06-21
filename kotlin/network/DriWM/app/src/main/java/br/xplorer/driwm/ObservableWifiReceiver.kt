package br.xplorer.driwm

import java.util.*


class ObservableWifiReceiver internal constructor() : Observable() {
    companion object {
        val INSTANCE = ObservableWifiReceiver()
    }

    fun updateValue(any: Any) {
        setChanged()
        notifyObservers(any)
    }
}