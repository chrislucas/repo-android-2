package com.br.funwithencriptsharedpreference.tutorial.medium.sharedpreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.funwithencriptsharedpreference.R

/**
 * https://medium.com/android-dev-br/sharedpreferences-d34768fcda45
 */
class PersistsSharedPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persists_shared_preference)
    }
}