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
import com.br.experience.basic.ui.theme.BasicTheme

var levelDashboardHierarchicalCombine1 = 0
var levelDashboardHierarchicalCombine2 = 0
var levelDashboardHierarchicalCombine3 = 0
var levelDashboardHierarchicalCombine4 = 0

@Composable
fun DashboardHierarchicalCombine() {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(Color.Gray)
    Column(modifier) {
        levelDashboardHierarchicalCombine1 += 1
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Recompose: $levelDashboardHierarchicalCombine1; Update: $isOn1",
            textAlign = TextAlign.Center
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.Red)
        ) {
            levelDashboardHierarchicalCombine2 += 1
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Recompose: $levelDashboardHierarchicalCombine2; Update: $isOn2",
                textAlign = TextAlign.Center
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.Green)
            ) {
                levelDashboardHierarchicalCombine3++
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Recompose: $levelDashboardHierarchicalCombine3; Update: $isOn3",
                    textAlign = TextAlign.Center
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.Yellow)
                ) {
                    levelDashboardHierarchicalCombine4++
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(color = Color.Yellow),
                        text = "Recompose: $levelDashboardHierarchicalCombine4; Update: $isOn4",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDashboardHierarchicalCombine() {
    BasicTheme {
        DashboardHierarchicalCombine()
    }
}