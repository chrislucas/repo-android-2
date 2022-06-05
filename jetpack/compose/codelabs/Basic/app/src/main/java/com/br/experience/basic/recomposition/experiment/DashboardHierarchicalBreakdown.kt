package com.br.experience.basic.recomposition.experiment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.experience.basic.recomposition.experiment.ui.theme.BasicTheme

var levelDashboardHierarchicalBreakdown1 = 0
var levelDashboardHierarchicalBreakdown2 = 0
var levelDashboardHierarchicalBreakdown3 = 0
var levelDashboardHierarchicalBreakdown4 = 0

@Preview(showBackground = true)
@Composable
fun DefaultPreviewDashboardHierarchicalBreakdown() {
    BasicTheme {
        DashboardHierarchicalBreakdown()
    }
}

@Composable
fun DashboardHierarchicalBreakdown() {
    Dashboard11()
}

@Composable
fun Dashboard11() {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(Color.Gray)
    Column(modifier) {
        levelDashboardHierarchicalBreakdown1 += 1
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Recompose: $levelDashboardHierarchicalBreakdown1; Update: $isOn1",
            textAlign = TextAlign.Center
        )
        Dashboard12()
    }
}

@Composable
fun Dashboard12() {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(Color.Red)
    Column(modifier) {
        levelDashboardHierarchicalBreakdown2 += 1
        Text(
            text = "Recompose: $levelDashboardHierarchicalBreakdown2; Update: $isOn2",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Dashboard13()
    }
}

@Composable
fun Dashboard13() {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(Color.Green)

    Column(modifier) {
        levelDashboardHierarchicalBreakdown3 += 1
        Text(
            text = "Recompose: $levelDashboardHierarchicalBreakdown3; Update: $isOn3",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Dashboard14()
    }
}

@Composable
fun Dashboard14() {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(Color.Yellow)

    Column(modifier) {
        levelDashboardHierarchicalBreakdown4 += 1
        Text(
            text = "Recompose: $levelDashboardHierarchicalBreakdown4; Update: $isOn4",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}