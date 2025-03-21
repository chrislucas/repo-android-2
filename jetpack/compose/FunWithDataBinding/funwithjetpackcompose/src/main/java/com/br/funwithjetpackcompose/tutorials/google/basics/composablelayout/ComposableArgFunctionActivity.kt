package com.br.funwithjetpackcompose.tutorials.google.basics.composablelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.tooling.preview.Preview
import com.br.funwithjetpackcompose.tutorials.google.basics.composablelayout.ui.theme.FunWithDataBindingTheme
import com.google.android.material.internal.FlowLayout


/*
    How Jetpack Compose Measuring Works
    https://developer.squareup.com/blog/how-jetpack-compose-measuring-works/

    TODO
    Debug your Compose UI
    https://developer.android.com/develop/ui/compose/tooling/debug
    Debug your layout with Layout Inspector
    https://developer.android.com/studio/debug/layout-inspector

 */
class ComposableArgFunctionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->

                }
            }
        }
    }
}

/*
    https://newsletter.jorgecastillo.dev/p/custom-layouts-measuring-policies
 */
@Composable
fun ComposableLayout(modifier: Modifier = Modifier, composable: @Composable () -> Unit) {
    Layout(
        content = composable,
        modifier = modifier,
        measurePolicy =  { measurables, constraints ->
            layout(constraints.maxWidth, constraints.maxHeight) {  }
        }
    )

}

@Composable
fun MyBasicColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = { m, c ->
            layout(c.maxWidth, c.maxHeight) {  }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {

    }
}