package com.br.justcomposelabs.allfeatures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.glance.appwidget.lazy.LazyVerticalGrid
import com.br.justcomposelabs.allfeatures.ui.theme.JustComposeLabsTheme

/*
    TODO
        - listar todas as funcionlidades exploradas no projeto
        - Criar um layout que permita que o estilo da lista possa ser modificado, de grid para lista e vice-versa
        - Criar um sistema de navegacao entre modulos para que possamos
        adicionar um card de uma funcionalidade de um modulo desse projeto
 */
class AllFeatureActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AllFeatures(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AllFeatures(
    modifier: Modifier = Modifier,
    features: List<String> = listOf()
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(200.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(features) { feature ->
                Card {
                    Text(feature)
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

/*
    https://developer.android.com/develop/ui/compose/lists
 */

@Preview(showBackground = true)
@Composable
fun AllFeaturesPreview() {
    JustComposeLabsTheme {
        val features = buildList {
            repeat(200) {
                add("Feature $it")
            }
        }
        AllFeatures(Modifier.fillMaxSize(), features)
    }
}