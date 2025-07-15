package com.br.funwithdatabinding.view.features.voiceassistant

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    https://github.com/ArezooNazer/VoiceInteractionSample/blob/master/app/src/main/java/com/arezoonazer/voiceinteractionsample/SpeechRecognizerActivity.kt
 */
class VoiceAssistantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_voice_assistant)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.coordinator_main_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}