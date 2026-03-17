package com.br.funwithjetpackcompose.tutorials.google.interoperabillity.usingviewsincompose.androidviewbinding

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.br.funwithjetpackcompose.tutorials.google.interoperabillity.usingviewsincompose.androidviewbinding.ui.theme.FunWithDataBindingTheme
import com.br.mylibrary.databinding.ActivityDrawingCubeBinding

/*
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/views-in-compose#androidview_with_view_binding
 */
class AndroidViewBindingInComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ApplyAndroidViewBinding(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ApplyAndroidViewBinding(modifier: Modifier = Modifier) {
    AndroidViewBinding(ActivityDrawingCubeBinding::inflate) {
        main.setBackgroundColor(Color.LTGRAY)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
        ApplyAndroidViewBinding()
    }
}