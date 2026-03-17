package com.br.experience.basic.recomposition.experiment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.br.experience.basic.recomposition.experiment.ui.theme.BasicTheme

var levelDashboardFlattenCombine1 = 0
var levelDashboardFlattenCombine2 = 0
var levelDashboardFlattenCombine3 = 0
var levelDashboardFlattenCombine4 = 0

@Preview(showBackground = true)
@Composable
fun PreviewDashboardFlattenCombine() {
    BasicTheme {
        DashboardFlattenCombine()
    }
}

@Composable
fun DashboardFlattenCombine() {
    Column(Modifier.fillMaxWidth()) {
        Column(Modifier.background(Color.Gray)) {
            levelDashboardFlattenCombine1 += 1
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Recompose: $levelDashboardFlattenCombine1; Update: $isOn1",
                textAlign = TextAlign.Center
            )
        }

        Column {
            levelDashboardFlattenCombine2 += 1
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Red),
                text = "Recompose: $levelDashboardFlattenCombine2; Update: $isOn2",
                textAlign = TextAlign.Center
            )
        }

        Column {
            levelDashboardFlattenCombine3 += 1
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Green),
                text = "Recompose: $levelDashboardFlattenCombine3; Update: $isOn3",
                textAlign = TextAlign.Center
            )
        }

        Column {
            levelDashboardFlattenCombine4 += 1
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Yellow),
                text = "Recompose: $levelDashboardFlattenCombine4; Update: $isOn4",
                textAlign = TextAlign.Center
            )
        }
    }
}