package com.br.justcomposelabs.tutorial.google.compose.intrinsicmeasurements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
    https://developer.android.com/develop/ui/compose/layouts/intrinsic-measurements
 */

@Composable
fun MinIntrinsicColumnSizeUndesiredResultComponent(
    modifier: Modifier = Modifier,
    first: String,
    second: String
) {
    Column(modifier.systemBarsPadding()) {
        Text(
            first, modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp),
            textAlign = TextAlign.Start
        )
        /*
        VerticalDivider(
            color = Color.Black,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

         */
        Text(second,
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp),
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MinIntrinsicColumnSizeUndesiredResultComponentPreview() {

}