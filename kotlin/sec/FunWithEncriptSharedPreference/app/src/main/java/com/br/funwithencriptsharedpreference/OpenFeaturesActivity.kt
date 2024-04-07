package com.br.funwithencriptsharedpreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.funwithencriptsharedpreference.databinding.ActivityOpenFeaturesBinding
import com.br.funwithencriptsharedpreference.tutorial.decrypt.EncryptedSharedPreferenceIActivity
import com.br.funwithencriptsharedpreference.tutorial.medium.sharedpreference.PersistsSharedPreferenceActivity

class OpenFeaturesActivity : AppCompatActivity() {

    private val bindView: ActivityOpenFeaturesBinding by lazy {
        ActivityOpenFeaturesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindView.root)

        with(bindView) {
            btnOpenEncryptedSharedPreferenceActivity.setOnClickListener {
                startActivity(
                    Intent(this@OpenFeaturesActivity, EncryptedSharedPreferenceIActivity::class.java)
                )
            }

            btnOpenPersistDataSharedPreference.setOnClickListener {
                startActivity(
                    Intent(this@OpenFeaturesActivity, PersistsSharedPreferenceActivity::class.java)
                )
            }
        }
    }
}