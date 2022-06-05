package com.br.experience.basic.recomposition.experiment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.br.experience.basic.ui.theme.BasicTheme

var levelControl1 = 0
var levelControl2 = 0
var levelControl3 = 0
var levelControl4 = 0

var isOn1 by mutableStateOf(0)
var isOn2 by mutableStateOf(0)
var isOn3 by mutableStateOf(0)
var isOn4 by mutableStateOf(0)

@Composable
fun Control() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
    ) {
        levelControl1 += 1
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Recompose: $levelControl1",
            textAlign = TextAlign.Center
        )
        Button(
            onClick = { isOn2 += 1 },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
        ) {
            Column {
                levelControl2 += 1
                Text(
                    text = "Recompose: $levelControl2",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = { isOn3++ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                ) {
                    Column {
                        levelControl3 += 1
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Recompose: $levelControl3",
                            textAlign = TextAlign.Center
                        )
                        Button(
                            onClick = { isOn4++ },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)
                        ) {
                            levelControl4 += 1
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Recompose: $levelControl4",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewControl() {
    BasicTheme {
        Control()
    }
}