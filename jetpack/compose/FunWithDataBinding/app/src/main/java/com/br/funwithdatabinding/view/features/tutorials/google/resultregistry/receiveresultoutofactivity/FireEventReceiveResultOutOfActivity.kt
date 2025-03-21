package com.br.funwithdatabinding.view.features.tutorials.google.resultregistry.receiveresultoutofactivity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.br.funwithdatabinding.R


/*
    https://developer.android.com/training/basics/intents/result
 */
class FireEventReceiveResultOutOfActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fire_event_receive_result_out_of)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}


class CustomLifecycleObserver<T>(
    private val registry: ActivityResultRegistry
) : DefaultLifecycleObserver {

    // TODO estudar como tornar essa classe parametrizavel

    lateinit var getContent: ActivityResultLauncher<T>

    override fun onCreate(owner: LifecycleOwner) {
        /*
            getContent = registry.register("key", owner, GetContent()) { uri ->
                // Handle the returned Uri
            }

         */
    }

    fun selectImage() {
        // getContent.launch()
    }
}