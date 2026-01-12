package com.br.funwithtensorflowlite.tutorial.google.tensorflow.litemodel.digitclassifier.start

import android.os.Bundle
import android.util.Xml
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.br.funwithtensorflowlite.R
import com.br.funwithtensorflowlite.tutorial.google.tensorflow.litemodel.digitclassifier.start.theme.FunWithTensorFlowLiteTheme
import com.divyanshu.draw.widget.DrawView
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.text.style.TextAlign


class DigitClassifierActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithTensorFlowLiteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DigitClassifierUI(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DigitClassifierUI(modifier: Modifier = Modifier) {
    val resources = LocalResources.current
    Column(modifier
        .systemBarsPadding()
    ) {

        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.9f),
            factory = { context ->
                val parser = resources.getXml(R.xml.empty)
                DrawView(context, Xml.asAttributeSet(parser))
            }
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.05f),
            text = "Please draw a digit",
            textAlign = TextAlign.Center
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.05f),

            onClick = {}) {
            Text("Clear")
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    FunWithTensorFlowLiteTheme {
        DigitClassifierUI()
    }
}


