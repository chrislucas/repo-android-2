package com.br.funwithdatabinding.view.features.tutorials.medium.rxandretrofitii.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.br.funwithdatabinding.R
import com.br.funwithdatabinding.view.features.utils.FirebaseRemoteConfig
import com.br.funwithdatabinding.BuildConfig
import com.br.funwithdatabinding.view.features.tutorials.medium.rxandretrofitii.view.viewmodel.RxSpTransViewModel
import kotlinx.coroutines.launch


/**
 * TODO vou usar api sptrans ao inves do tutorial
 * https://medium.com/@nishargrocks007/using-retrofit-and-rxjava-in-your-android-project-8225f54df614
 */
class TutorialRxAndroidAndRetrofitIIActivity : AppCompatActivity() {


    private val viewModel: RxSpTransViewModel by viewModels<RxSpTransViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tutorial_rx_android_and_retrofit_iiactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        viewModel.observerIsAuthenticated.observe(this) { isAuthenticated ->
            Log.d("AUTH_SP_TRANS", "$isAuthenticated")
        }

        val firebaseRemoteConfig = FirebaseRemoteConfig()
        firebaseRemoteConfig.fetchAndActivate(this) { task ->

            val message = if (task.isSuccessful) {
                val token = firebaseRemoteConfig.spTransToken
                if (BuildConfig.DEBUG) {
                    Log.d(
                        "TOKEN",
                        "Token: $token"
                    )
                }

                token?.let {
                    viewModel.checkAuthentication(it)
                }
                "FetchAndActivate succeeded ${task.result}"
            } else {
                "FetchAndActivate Failed"
            }
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}