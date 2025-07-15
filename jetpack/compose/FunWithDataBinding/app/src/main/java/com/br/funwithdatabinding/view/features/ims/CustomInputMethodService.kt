package com.br.funwithdatabinding.view.features.ims

import android.inputmethodservice.InputMethodService
import android.view.KeyEvent
import android.view.View
import com.br.funwithdatabinding.R

/*
    create monitoring keyboard android
    https://developer.android.com/reference/android/inputmethodservice/InputMethodService

    https://stackoverflow.com/questions/2986337/is-it-possible-to-create-an-android-service-that-listens-for-hardware-key-presse
 */
class CustomInputMethodService: InputMethodService() {
    override fun onCreateInputView(): View? {
        return layoutInflater.inflate(R.layout.my_custom_keyboard, null)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyLongPress(keyCode, event)
    }

    override fun onKeyMultiple(keyCode: Int, count: Int, event: KeyEvent?): Boolean {
        return super.onKeyMultiple(keyCode, count, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyUp(keyCode, event)
    }
}