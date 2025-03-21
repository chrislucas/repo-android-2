package com.br.funwithjetpackcompose.tutorials.medium.testing.testinglayout.listbuttons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.funwithjetpackcompose.tutorials.medium.testing.testinglayout.listbuttons.ui.theme.FunWithDataBindingTheme

/*
    TODO
    https://developer.android.com/develop/ui/compose/lists
 */
class ListButtonsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RowAndColumnButtonTypes(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RowButtonTypes(modifier: Modifier = Modifier) {
    /*
        https://developer.android.com/develop/ui/compose/components/button
     */

    val padding5 = Modifier.padding(5.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Unspecified)
            .border(width = 2.dp, color = Color.Red)
    ) {
        Text("Row", modifier = Modifier.align(Alignment.CenterHorizontally))

        Row(
            modifier = modifier.horizontalScroll(rememberScrollState())
        ) {
            Spacer(modifier = padding5)

            Button({}, modifier = padding5) {
                Text("Button")
            }

            FilledTonalButton({}, modifier = padding5) {
                Text("Filled-Tonal-Button")
            }

            OutlinedButton({}, modifier = padding5) {
                Text("Outlined-Button")
            }

            ElevatedButton({}, modifier = padding5) {
                Text("Outlined-Button")
            }

            TextButton({}, modifier = padding5) {
                Text("Text-Button")
            }
        }
    }
}

@Composable
private fun ColumnButtonTypes(modifier: Modifier = Modifier) {
    /*
        https://developer.android.com/develop/ui/compose/components/button

        Scroll
        https://developer.android.com/develop/ui/compose/touch-input/pointer-input/scroll?hl=pt-br
     */

    @Composable
    fun ShowDivider() =
        Divider(
            thickness = 1.dp,
            color = Color.Blue,
            modifier = Modifier.padding(5.dp)
        )

    Column(
        modifier =
            modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .border(width = 2.dp, color = Color.Red)
    ) {

        Text("Column", modifier = modifier.align(Alignment.CenterHorizontally))
        ShowDivider()

        Button({}, modifier = modifier) {
            Text("Button")
        }
        ShowDivider()


        FilledTonalButton({}) {
            Text("Filled-Tonal-Button")
        }
        ShowDivider()


        OutlinedButton({}) {
            Text("Outlined-Button")
        }
        ShowDivider()


        ElevatedButton({}) {
            Text("Outlined-Button")
        }
        ShowDivider()


        TextButton({}) {
            Text("Text-Button")
        }
        ShowDivider()
    }
}


@Composable
private fun RowAndColumnButtonTypes(modifier: Modifier = Modifier) {


    Column {
        RowButtonTypes(modifier)
        /*
            https://www.geeksforgeeks.org/spacer-in-android-jetpack-compose/
         */
        Spacer(modifier = Modifier.height(30.dp))
        ColumnButtonTypes(modifier)
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
        RowAndColumnButtonTypes()
    }
}