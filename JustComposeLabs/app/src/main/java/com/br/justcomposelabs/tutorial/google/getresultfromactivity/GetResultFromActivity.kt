package com.br.justcomposelabs.tutorial.google.getresultfromactivity

import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

/*
    https://developer.android.com/training/basics/intents/result
 */


/*
Estudar para ver se da para criar uma extension function
fun <O : Any> AppCompatActivity.ff(
    registry: ActivityResultRegistry,
    callback: ActivityResultLauncher<O>
) =
    registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        registry = registry,
        callback = callback as ActivityResultCallback<ActivityResult>
    )

 */
