package com.br.justcomposelabs.tutorial.google.compose.color.lerp

import androidx.collection.mutableFloatSetOf
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.allfeatures.ui.theme.JustComposeLabsTheme
import com.br.justcomposelabs.utils.composable.paddingEdgeToEdge

/*
    create color from lerp function android compose
    https://www.google.com/search?q=create+color+from+lerp+function+android+compose&rlz=1C5GCEA_enBR1109BR1109&gs_lcrp=EgZjaHJvbWUyCQgAEEUYORigATIGCAEQIRgV0gEINjExNGowajGoAgCwAgA&sourceid=chrome&ie=UTF-8&udm=50&fbs=AIIjpHydJdUtNKrM02hj0s4nbm4yAFb4PvhjIUcDtaFHkK_tyspyDJg0-Y4Ji8bGEtEDNJF_QLt8mYL1Ky-sHdgJivrE677A-6HSRV-kn1DsqyQa91SRgsk7q-L5n4c0pDCrTlDhalcoTtr8AVUxm8-50qKLbcJa1oFC3zedeUJnfRdBbSAeHsPihVtS5uYQbTX437kSnfhOciNXdfFB8I4S2aQvmi7vBg&ved=2ahUKEwjutJ7h7eyPAxVrlZUCHa7IK3QQ0NsOegQIKBAA&aep=10&ntc=1&mstk=AUtExfD1WZDI0CenoEC2IWCDaDrFKPV48sW3pJWgtAP80I5hdCRAQrawkHdU-YgYbNrv6-7DbWYlM_DO1G_sTRQa-R-VLWr8gzfoavzUc5vCspX97ABzx420yrXzt5vCXlcCGc3NX_oH0B8hRjteI1YtZJJZh_z4cyvATzLrFpc47G-ZIXs4WfQ0iomU5A3lChJSMQEe859wBoft4vwTLzZR6t7Xs3fsW4TTKgTrCxhs4YidGwhip6o1V7PTWQ&csuir=1&safe=active&ssui=on

    A funcao lerp é uma funcao de interpolacao inear que calcula um valor entre dois intervalos,
    inicio e fim, baseado num dado parametro denominado fraction

 */

@Preview(showBackground = true)
@Composable
fun LerpColorExample() {
    val start = Color(0xFFB388FF)
    val end = Color(0xFFA7FFEB)

    var sliderPosition by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        val interpolatedColor = lerp(
            start,
            end,
            sliderPosition
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(interpolatedColor)

        ) {

            interpolatedColor.run {
                val red = red * 255
                val green = green * 255
                val blue = blue * 255

                val redHex = red.toInt().toString(16)
                val greenHex = green.toInt().toString(16)
                val blueHex = blue.toInt().toString(16)

                /*
                    val redHex = toHex(red.toInt())
                    val greenHex = toHex(green.toInt())
                    val blueHex = toHex(blue.toInt())
                 */

                Text(
                    "RGB($red, $green, $blue)\n$redHex$greenHex$blueHex",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..1f,
            modifier = Modifier.padding(16.dp)
        )
    }


}

fun toHex(value: Int): String =
    String.format("%s%s", Integer.toHexString(value / 16), Integer.toHexString(value % 16))
