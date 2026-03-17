package com.br.experience.basic.recomposition.experiment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.experience.basic.recomposition.experiment.ui.theme.BasicTheme

class RecompositionExperimentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicTheme {
                ShowTestRecomposition()
            }
        }
    }
}

@Composable
fun ShowTestRecomposition() {
    Column(Modifier.fillMaxWidth()) {
        Text(text = "Control Buttons")
        Control()
        Text(text = "Hierarchical breakdown composables")
        DashboardHierarchicalBreakdown()
        Text(text = "Flatten breakdown composables")
        DashboardFlattenBreakdown()
        Text(text = "Hierarchical combine composables")
        DashboardHierarchicalCombine()
        Text(text = "Flatten combine composables")
        DashboardFlattenCombine()
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    BasicTheme {
        ShowTestRecomposition()
    }
}