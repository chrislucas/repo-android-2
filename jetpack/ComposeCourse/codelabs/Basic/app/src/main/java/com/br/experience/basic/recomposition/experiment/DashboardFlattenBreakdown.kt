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

var levelDashboardFlattenBreakdown1 = 0
var levelDashboardFlattenBreakdown2 = 0
var levelDashboardFlattenBreakdown3 = 0
var levelDashboardFlattenBreakdown4 = 0

@Preview(showBackground = true)
@Composable
fun DefaultPreviewDashboardFlattenBreakdown() {
    BasicTheme {
        DashboardFlattenBreakdown()
    }
}

@Composable
fun DashboardFlattenBreakdown() {
    Column(Modifier.fillMaxWidth()) {
        Dashboard21()
        Dashboard22()
        Dashboard23()
        Dashboard24()
    }
}

@Composable
fun Dashboard21() {
    Column(Modifier.background(Color.Gray)) {
        levelDashboardFlattenBreakdown1 += 1
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Recompose: $levelDashboardFlattenBreakdown1; Update: $isOn1",
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Dashboard22() {
    Column {
        levelDashboardFlattenBreakdown2 += 1
        Text(
            modifier = Modifier.fillMaxWidth().background(color = Color.Red),
            text = "Recompose: $levelDashboardFlattenBreakdown2; Update: $isOn2",
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Dashboard23() {
    Column {
        levelDashboardFlattenBreakdown3 += 1
        Text(
            modifier = Modifier.fillMaxWidth().background(color = Color.Green),
            text = "Recompose: $levelDashboardFlattenBreakdown3; Update: $isOn3",
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Dashboard24() {
    Column {
        levelDashboardFlattenBreakdown4 += 1
        Text(
            modifier = Modifier.fillMaxWidth().background(color = Color.Yellow),
            text = "Recompose: $levelDashboardFlattenBreakdown4; Update: $isOn4",
            textAlign = TextAlign.Center
        )
    }
}