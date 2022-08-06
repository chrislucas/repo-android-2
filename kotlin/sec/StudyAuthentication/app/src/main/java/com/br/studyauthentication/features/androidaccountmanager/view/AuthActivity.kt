package com.br.studyauthentication.features.androidaccountmanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.studyauthentication.R

/**
 * https://developer.android.com/training/id-auth/identify
 */

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}