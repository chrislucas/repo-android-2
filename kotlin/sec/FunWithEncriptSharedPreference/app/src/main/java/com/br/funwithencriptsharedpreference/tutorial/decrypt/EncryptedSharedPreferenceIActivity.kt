package com.br.funwithencriptsharedpreference.tutorial.decrypt

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.Spanned
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.br.funwithencriptsharedpreference.R
import com.br.funwithencriptsharedpreference.databinding.ActivityEncryptedSharedPreferenceIactivityBinding
import com.br.funwithencriptsharedpreference.tutorial.helper.createEncryptedSharedPreferences
import com.br.funwithencriptsharedpreference.tutorial.helper.providerEncryptedSharedPreference
import java.io.File
import kotlin.system.measureTimeMillis

/**
 * https://github.com/akaita/encryptedsharedpreferences-example/blob/master/app/src/main/java/com/akaita/encryptedsharedpreferences/MainActivity.kt
 * https://github.com/akaita/encryptedsharedpreferences-example/tree/master
 *
 */
class EncryptedSharedPreferenceIActivity : AppCompatActivity() {

    private fun String.highLighting(): Spanned =
        kotlin.run {
            /**
             * Estudar sobre backreference em regex
             * https://www.regular-expressions.info/backref.html
             */
            val highlighted = this.replace("<([^>/]*)/>".toRegex(), "&lt;~green~\$1~/~&gt;")
                .run {
                    replace("<([^>]*)>".toRegex(), "&lt;~blue~\$1~/~&gt;")
                }.run {
                    replace("(\\w+)=\"([^\"]*)\"".toRegex(), "~red~\$1~/~~black~=\"~/~~green~$2~/~~black~\"~/~")
                }
                .run {
                    replace("~([a-z]+)~".toRegex(), "<span style=\"color: \$1;\">")
                }
                .run {
                    if (Log.isLoggable("HIGHLIGHTING", Log.INFO)) {
                        Log.i("HIGHLIGHTING", this)
                    }
                    replace("~/~".toRegex(), "</span>")
                }

            Log.i("HIGHLIGHT_CONTENT", highlighted)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(highlighted, Html.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(highlighted)
            }
        }

    companion object {
        const val SHARED_FILE_NAME = "sample_encrypted_shared_preference"
        const val KEY_ENCRYPTED_STRING = "key_encrypted_string"
        const val KEY_IS_PERFORMANCE_TEST_ENABLED = "key_is_performance_test_enabled"
    }

    private val bindView: ActivityEncryptedSharedPreferenceIactivityBinding by lazy {
        ActivityEncryptedSharedPreferenceIactivityBinding.inflate(layoutInflater)
    }

    private lateinit var sharedPreference: SharedPreferences

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                showAboutDialog()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun showAboutDialog() {
        with(AlertDialog.Builder(this)) {
            setIcon(R.mipmap.ic_launcher)
            setTitle(getString(R.string.app_name))
            setView(layoutInflater.inflate(R.layout.about, bindView.root))
            setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog.dismiss()
            }
            create().show()
        }
    }

    override fun onStart() {
        super.onStart()
        initCleartextSharedPreferences()
        Log.i("ON_START", "ON_START")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindView.root)
        Log.i("ON_CREATE", "ON_CREATE")

        // init listener views
        with(bindView) {
            initEncrypted.setOnCheckedChangeListener { _, checked ->
                resetSharedPreference(checked)
            }

            saveText.doAfterTextChanged {
                it?.let {
                    if (Log.isLoggable("SAVE_STRING", Log.INFO)) {
                        Log.i("SAVE_STRING", it.toString())
                    }
                    execute(saveTimestamp) {
                        sharedPreference
                            .edit()
                            .putString(KEY_ENCRYPTED_STRING, it.toString())
                            .apply()
                    }

                    execute(bindView.readTimestamp) {
                        bindView.readText.setText(
                            sharedPreference.getString(KEY_ENCRYPTED_STRING, "*")
                        )
                    }
                }
            }

            performanceMode.setOnCheckedChangeListener { _, checked ->
                sharedPreference
                    .edit()
                    .putBoolean(KEY_IS_PERFORMANCE_TEST_ENABLED, checked)
                    .apply()
            }
        }
    }

    private fun execute(textView: TextView, fn: () -> Unit) {
        measureTimeMillis {
            fn()
        }.let {
            setSpentTime(it, textView)
        }
        showRawFile()
    }

    private fun resetSharedPreference(isInitEncryptedChecked: Boolean) {
        resetSharedPreferences()
        if (!isInitEncryptedChecked) {
            initCleartextSharedPreferences()
        } else {
            initEncryptedSharedPreferences()
        }
        showRawFile()
    }

    private fun initEncryptedSharedPreferences() {
        measureTimeMillis {
            sharedPreference = providerEncryptedSharedPreference(
                SHARED_FILE_NAME,
                this,
                ::createEncryptedSharedPreferences
            )
        }.let {
            setSpentTime(it, bindView.initTimestamp)
        }
    }

    private fun initCleartextSharedPreferences() {
        measureTimeMillis {
            sharedPreference = getSharedPreferences(SHARED_FILE_NAME, MODE_PRIVATE)
        }.let {
            setSpentTime(it, bindView.initTimestamp)
        }
    }

    private fun setSpentTime(spentTime: Long, textView: TextView) {
        textView.visibility = View.VISIBLE
        textView.text = getString(R.string.timestamp).format(spentTime)
    }

    private fun resetSharedPreferences() {
        /*
            Consider using apply() instead; commit writes its data to persistent storage immediately,
            whereas apply will handle it in the background
         */

        sharedPreference.getBoolean(KEY_IS_PERFORMANCE_TEST_ENABLED, true).let {
            if (it) {
                sharedPreference.edit()
                    .clear()
                    //.apply()
                    // com o objetivo de fazer um teste performance usamos o
                    .apply()
            } else {
                sharedPreference.edit()
                    .clear()
                    //.apply()
                    // com o objetivo de fazer um teste performance usamos o
                    .commit()
            }

            sharedPreference
                .edit()
                .putBoolean(KEY_IS_PERFORMANCE_TEST_ENABLED, it)
        }
    }

    private fun showRawFile() {
        val path = "${applicationInfo.dataDir}/shared_prefs/$SHARED_FILE_NAME.xml"

        /*
            https://developer.android.com/reference/android/util/Log#isLoggable(java.lang.String,%20int)
         */
        if (Log.isLoggable("SHARED_PREFERENCE", Log.INFO)) {
            Log.i("SHARED_PREFERENCE", "pathfile: $path")
        }

        bindView.pathFile.text = getString(R.string.path_file, path)
        bindView.fileText.text =
            File(path)
                .run {
                    if (exists()) readText().highLighting() else ""
                }
    }
}




