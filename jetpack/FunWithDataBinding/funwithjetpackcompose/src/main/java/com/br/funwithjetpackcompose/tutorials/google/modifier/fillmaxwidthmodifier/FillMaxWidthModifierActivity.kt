package com.br.funwithjetpackcompose.tutorials.google.modifier.fillmaxwidthmodifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.funwithjetpackcompose.tutorials.google.modifier.fillmaxwidthmodifier.ui.theme.FunWithDataBindingTheme

/*
    https://stackoverflow.com/questions/65942711/match-width-of-parent-in-column-jetpack-compose
 */
class FillMaxWidthModifierActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BoxMaxWidthModifier()
                }
            }
        }
    }
}


@Composable
fun BoxMaxWidthModifier() {

    val shortText = "Texto Curto"
    val extremelyLongText = "Dado o fluxo de dados atual, a normalização da data causou o bug na interpolação dinâmica de strings."
    val mediumText = "Dado o fluxo de dados atual"

    Column {
        Column (Modifier.width(IntrinsicSize.Max)) {
            Box(Modifier.fillMaxWidth().background(Color.Gray)) {
                Text(shortText)
            }

            Box(Modifier.fillMaxWidth().background(Color.Blue)) {
                Text(extremelyLongText)
            }


            Box(Modifier.fillMaxWidth().background(Color.Magenta)) {
                Text(mediumText)
            }
        }

        Spacer(Modifier.height(10.dp))

        // https://developer.android.com/develop/ui/compose/layouts/intrinsic-measurements

        val minWidth = Modifier.width(IntrinsicSize.Min)
        Column (Modifier.width(IntrinsicSize.Max)) {
            Box(minWidth.background(Color.Gray)) {
                Text(shortText)
            }

            Box(minWidth.background(Color.Blue)) {
                Text(extremelyLongText)
            }


            Box(minWidth.background(Color.Magenta)) {
                Text(mediumText)
            }
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
        BoxMaxWidthModifier()
    }
}