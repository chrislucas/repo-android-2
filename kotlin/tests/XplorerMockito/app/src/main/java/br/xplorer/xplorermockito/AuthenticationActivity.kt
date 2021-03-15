package br.xplorer.xplorermockito

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import br.xplorer.xplorermockito.utils.isEmpty
import kotlinx.android.synthetic.main.activity_authentication.*

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
    }


    fun authentication(view: View) {
        when(view.id) {
            R.id.button_authentication -> {
                if(validateForm()) {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }
    }


    private fun validateIfEmptyAndExeuteFun(map: Map<EditText, () -> Unit>) : Boolean {
        for ((editText, fn) in map) {
            if (editText.isEmpty()) {
                fn()
                return false
            }
        }
        return true
    }

    private fun validateForm() : Boolean {
        val map = mapOf(
            edit_text_user_id to fun () {
                edit_text_user_id.error = "O @id do usuairo é obrigatório"
                edit_text_user_id.requestFocus()
            }
            , edit_text_password to fun () {
                edit_text_password.error = "A senha é obrigatória"
                edit_text_password.requestFocus()
            }
        )
        return validateIfEmptyAndExeuteFun(map)
    }
}
