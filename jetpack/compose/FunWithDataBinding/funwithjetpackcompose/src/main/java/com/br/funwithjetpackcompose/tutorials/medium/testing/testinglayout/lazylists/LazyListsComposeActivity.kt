package com.br.funwithjetpackcompose.tutorials.medium.testing.testinglayout.lazylists

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.funwithjetpackcompose.tutorials.medium.testing.testinglayout.lazylists.ui.theme.FunWithDataBindingTheme

/*
    https://developer.android.com/develop/ui/compose/lists
 */
class LazyListsComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyList(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LazyList(modifier: Modifier = Modifier) {
    /*
        https://developer.android.com/develop/ui/compose/lists#lazy

        - Para listas com muitos elementos nao é apropriado usar a funcao
        Column, pois pode ter uma performance ruim. Isso porque todos os items
        da lista serão desenhados e gastaram recursos computacionais sendo visiveis
        ou nao na tela.

        - Compose fornece componentes que serao comporao e desenharao items na tela que estao
        visiveis

            - LazyColumn e LazyRow sao 2 desses componentes
     */


    @Composable
    fun CenteredText(value: String) =
        Text(
            text = value,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
        )

    @Composable
    fun VerticalScroll() {
        return LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.Unspecified)
                .border(width = 2.dp, color = Color.Red)
        ) {
            item {
                CenteredText("First")
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.Blue
                )
            }

            items(6) {
                CenteredText("Item $it")
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.Blue
                )
            }

            item {
                CenteredText("Last")
            }
        }
    }


    @Composable
    fun HorizontalScroll() {
        return LazyRow(
            modifier =
                modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .background(color = Color.Unspecified)
                    .border(width = 2.dp, color = Color.Red)
        ) {
            item {
                CenteredText("First")
                VerticalDivider(thickness = 1.dp, color = Color.Blue)
            }

            items(6) {
                CenteredText("Item $it")
                VerticalDivider(thickness = 1.dp, color = Color.Blue)
            }

            item {
                CenteredText("Last")
            }
        }
    }

    Column {

        VerticalScroll()

        HorizontalDivider(
            modifier = Modifier.padding(10.dp),
            thickness = 1.dp,
            color = Color.Blue
        )

        HorizontalScroll()
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
        LazyList()
    }
}