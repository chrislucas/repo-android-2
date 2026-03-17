package com.br.funwithdatabinding.view.features.tutorials.google.activityresultapi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    TODO
    https://developer.android.com/training/basics/intents/result
 */
class TryAllDefaultActivityResultApiActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_try_all_default_result_api)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
            TODO adicionar varios botoes para disparar esses registros
         */

        val startActivityForResult = registerForActivityResult(StartActivityForResult()) { result ->

        }

        val startIntentSenderForResult = registerForActivityResult(StartIntentSenderForResult()) { result ->

        }

        val requestMultiplePermissions = registerForActivityResult(RequestMultiplePermissions()) { result ->
            /*
                https://developer.android.com/training/permissions/requesting?hl=pt-br
             */
        }

        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
            /*
                 https://developer.android.com/training/basics/intents/result?hl=pt-br

             */
        }
    }
}