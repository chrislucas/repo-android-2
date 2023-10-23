package com.br.funwithencriptsharedpreference.tutorial.ext

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.hideKeyboard() =
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).let {
        it.hideSoftInputFromWindow(findViewById<View>(android.R.id.content).windowToken, 0)
    }