package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava3.features.authentication.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.BuildConfig
import com.br.funwithdatabinding.R
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.service.FirebaseConfigRepository
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava3.features.authentication.view.viewmodel.AuthenticationSpTransApiViewModel

class AuthenticationSpTransApiActivity : AppCompatActivity() {

    private val viewModel by viewModels<AuthenticationSpTransApiViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_authentication_sp_trans_api)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recycler_view_all_features)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        FirebaseConfigRepository.fetchToken(this) { token ->
            val message = token?.let {
                viewModel.checkAuthentication(it)
                /*
                    passando um observer como argumento
                 */
                viewModel.getObserverIsAuthenticated().observe(this) { verify ->
                    if (BuildConfig.DEBUG) {
                        Log.d("TOKEN", "Is Authenticated? $verify")
                    }
                }
                "Token: $it"
            } ?: run {
                "Token: null"
            }
            if (BuildConfig.DEBUG) {
                Log.d("TOKEN", message)
            }
        }
    }
}