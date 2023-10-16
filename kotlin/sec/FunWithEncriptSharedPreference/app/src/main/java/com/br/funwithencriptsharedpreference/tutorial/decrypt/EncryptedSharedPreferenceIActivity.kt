package com.br.funwithencriptsharedpreference.tutorial.decrypt

import android.app.Activity
import android.content.SharedPreferences
import android.inputmethodservice.InputMethodService
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.br.funwithencriptsharedpreference.R
import com.br.funwithencriptsharedpreference.databinding.ActivityEncryptedSharedPreferenceIactivityBinding
import com.br.funwithencriptsharedpreference.tutorial.helper.ProviderEncryptedSharedPreferences
import com.br.funwithencriptsharedpreference.tutorial.helper.providerEncryptedSharedPreference
import com.br.funwithencriptsharedpreference.tutorial.helper.providerEncryptedSharedPreferenceDefault
import java.io.File

class EncryptedSharedPreferenceIActivity : AppCompatActivity() {

    fun String.highlight(): Spanned =
        kotlin.run {
            val highlighted = this.replace("<([^>/]*)/>".toRegex(), "&lt;~blue~\$1~/~/&gt;")
                .also {
                    it.replace("<([^>]*)>".toRegex(), "&lt;~blue~\$1~/~&gt;")
                }.also {
                    it.replace("([\\\\w]+)=\"([^\"]*)\"".toRegex(), "~red~\$1~/~~black~=\\\"~/~~green~\$2~/~~black~\\\"~/~")
                }.also {
                    it.replace("~([a-z]+)~".toRegex(), "<span style=\\\"color: \$1;\\\">")

                }.also {
                    it.replace("~/~".toRegex(), "</span>")
                }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(highlighted, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(highlighted)
            }
        }

    fun AppCompatActivity.hideKeyboard() =
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).let {
            it.hideSoftInputFromWindow(findViewById<View>(android.R.id.content).windowToken, 0)
        }

    companion object {
        const val SHARED_FILE_NAME = "sample_shared_filename"
    }

    private val bindView: ActivityEncryptedSharedPreferenceIactivityBinding by lazy {
        ActivityEncryptedSharedPreferenceIactivityBinding.inflate(layoutInflater)
    }

    private val sharedPreference: SharedPreferences by lazy {
        providerEncryptedSharedPreference(SHARED_FILE_NAME, ::check)
    }

    private val encryptedSharedPreferences: SharedPreferences
            by ProviderEncryptedSharedPreferences("") { filename ->

                val providerDefaultMasterKeyAlias = MasterKey
                    .Builder(this)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build()


                EncryptedSharedPreferences.create(
                    this,
                    filename,
                    providerDefaultMasterKeyAlias,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
                )
            }

    private fun check(filename: String): SharedPreferences {
        /*
    https://proandroiddev.com/encrypted-preferences-in-android-af57a89af7c8
    Criar a masterke
 */

        val providerDefaultMasterKeyAlias = MasterKey
            .Builder(this)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        /*
            https://proandroiddev.com/encrypted-preferences-in-android-af57a89af7c8

            Inicializar o EncryptedSharedPreference
         */

        return EncryptedSharedPreferences.create(
            this,
            filename,
            providerDefaultMasterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindView.root)

        with(bindView) {
            initEncrypted.setOnCheckedChangeListener { _, checked ->

            }

            saveButton.setOnClickListener {

            }

            readButton.setOnClickListener {

            }
        }
    }

    private fun initSharedPreference(isInitEncryptedChecked: Boolean) {

        if (isInitEncryptedChecked) {

        } else {

        }

        hideKeyboard()
        showRawFile()
    }

    private fun showRawFile() {
        val pathfile : String = "${applicationInfo.dataDir}/shared_prefs/$SHARED_FILE_NAME.xml"

        /*
            https://developer.android.com/reference/android/util/Log#isLoggable(java.lang.String,%20int)
         */
        if (Log.isLoggable("SHARED_PREFERENCE", Log.INFO)) {
            Log.i("SHARED_PREFERENCE", "pathfile: $pathfile")
        }
        bindView.fileText.text =
            File(pathfile)
                .run {
                    if (exists()) {
                        readText().highlight()
                    } else {
                        ""
                    }
                }
    }
}




