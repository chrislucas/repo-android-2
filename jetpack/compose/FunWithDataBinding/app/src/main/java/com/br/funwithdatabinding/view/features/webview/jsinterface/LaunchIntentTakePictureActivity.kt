package com.br.funwithdatabinding.view.features.webview.jsinterface

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

class LaunchIntentTakePictureActivity : AppCompatActivity() {


    /**
     * @see LaunchIntentFromJsActivity
        TODO
        Abrir a caâmera via JS, tirar uma foto e recuperar o conteúdo utilizando
         - ActivityResultContracts.TakePicture
     */
    private val registerForTakeAPicture = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { result ->
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_launch_intent_take_picture)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}