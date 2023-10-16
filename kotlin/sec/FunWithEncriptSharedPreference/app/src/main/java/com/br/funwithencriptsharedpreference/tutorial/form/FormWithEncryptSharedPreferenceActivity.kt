package com.br.funwithencriptsharedpreference.tutorial.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.funwithencriptsharedpreference.R

/**
 * TODO Criar um formulario
 * Se o check box for marcado
 *  - Salvar no sharedpreference que o usuario quer gravar seus dados
 *  - Ao clicar no botao, validar se os campos estao preenchidos, se esiverem salvar name e nickname no sharedpreference
 *
 *
 */
class FormWithEncryptSharedPreferenceActivity : AppCompatActivity() {


    companion object ConstUserPreferences {
        const val KEY_NAME = "name"
        const val KEY_NICKNAME = "name"
        const val IS_ENABLE_TO_SAVE_USER_DATA = "is_enable_to_save_user_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_with_encripty_shared_preference)
    }
}