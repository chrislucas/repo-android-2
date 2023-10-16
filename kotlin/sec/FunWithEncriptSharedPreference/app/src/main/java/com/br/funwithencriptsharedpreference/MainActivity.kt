package com.br.funwithencriptsharedpreference

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.br.funwithencriptsharedpreference.tutorial.helper.providerEncryptedSharedPreferenceDefault

/**
 * https://proandroiddev.com/encrypted-preferences-in-android-af57a89af7c8
 * https://developer.android.com/topic/security/best-practices
 * https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences
 * https://medium.com/nerd-for-tech/secure-sharedpreferences-android-9ba1e59f4250
 *
 */
class MainActivity : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by lazy {
        providerEncryptedSharedPreferenceDefault("encrypted_shared_preference_default")
    }

    companion object {
        const val DEFAULT_KEY_HW = "DKHW"
        const val DEFAULT_KEY_TEST2 = "TEST2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recoveryDataFromSharedPreference()
    }

    private fun recoveryDataFromSharedPreference() {
        with(sharedPreferences) {
            edit()
                .putString(DEFAULT_KEY_HW, "Hello World")
                /*
                    apply X commit
                 */
                .apply()

            edit()
                .putString(DEFAULT_KEY_TEST2, "COMMITED")
                /*
                    apply X commit
                 */
                .commit()
        }

        Toast.makeText(
            this,
            sharedPreferences.getString(DEFAULT_KEY_TEST2, "???"), // lendo o dado gravado
            Toast.LENGTH_LONG
        ).show()
    }
}