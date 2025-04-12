package com.br.funwithdatabinding.view.features.tutorials.stackoverflow.clickablespan

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R


/*
    TODO
        ler e implementar uma resposta que faz multiplas substrings numa string serem clicavel
        e terem comportamentos diferentes

    https://stackoverflow.com/questions/10696986/how-to-set-the-part-of-the-text-view-is-clickable

    Highlight on ClickableSpan click
    https://stackoverflow.com/questions/5595785/highlight-on-clickablespan-click/19445108#19445108
 */
class PartOfStringClickableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_part_of_string_clickable)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}