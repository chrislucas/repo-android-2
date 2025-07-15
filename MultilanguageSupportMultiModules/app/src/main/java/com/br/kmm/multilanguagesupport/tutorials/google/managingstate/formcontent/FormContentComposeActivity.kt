package com.br.kmm.multilanguagesupport.tutorials.google.managingstate.formcontent

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.kmm.multilanguagesupport.tutorials.google.managingstate.FormContent
import com.br.kmm.multilanguagesupport.tutorials.google.managingstate.formcontent.ui.theme.MultilanguageSupportTheme

class FormContentComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultilanguageSupportTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FormContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Preview(
    showSystemUi = true, showBackground = true, name = "Form Content", uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun PreviewFormContent() {
    Surface {
        FormContent(Modifier.fillMaxSize())
    }
}