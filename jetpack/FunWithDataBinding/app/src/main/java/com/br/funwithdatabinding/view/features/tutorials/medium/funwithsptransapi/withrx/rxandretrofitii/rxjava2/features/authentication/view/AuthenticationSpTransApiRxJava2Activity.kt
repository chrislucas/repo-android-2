package com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.authentication.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R
import com.br.funwithdatabinding.BuildConfig
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.commons.service.FirebaseConfigRepository
import com.br.funwithdatabinding.view.features.tutorials.medium.funwithsptransapi.withrx.rxandretrofitii.rxjava2.features.authentication.view.viewmodel.AuthenticationSpTransApiViewModel


/**
 * TODO Usar api sptrans ao inves do tutorial
 * https://medium.com/@nishargrocks007/using-retrofit-and-rxjava-in-your-android-project-8225f54df614
 */
class AuthenticationSpTransApiRxJava2Activity : AppCompatActivity() {

    private val viewModel: AuthenticationSpTransApiViewModel by viewModels<AuthenticationSpTransApiViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tutorial_rx_android_and_retrofit_iiactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recycler_view_all_features)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.isAuthenticated.observe(this) { isAuthenticated ->
            if (BuildConfig.DEBUG) {
                Log.d("AUTH_SP_TRANS", "$isAuthenticated")
            }
        }

        FirebaseConfigRepository.fetchToken(this) { token ->
            val message = token?.let {
                viewModel.checkAuthentication(it)
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