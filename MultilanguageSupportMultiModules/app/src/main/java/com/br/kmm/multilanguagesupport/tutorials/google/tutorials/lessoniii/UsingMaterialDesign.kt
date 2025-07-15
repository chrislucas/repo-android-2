package com.br.kmm.multilanguagesupport.tutorials.google.tutorials.lessoniii

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.kmm.multilanguagesupport.tutorials.google.tutorials.lessonii.Message
import com.br.kmm.multilanguagesupport.tutorials.google.tutorials.lessonii.MessageCard
import com.br.kmm.multilanguagesupport.ui.theme.MultilanguageSupportTheme

/*
    Use Material Design
    https://developer.android.com/develop/ui/compose/tutorial
    https://developer.android.com/develop/ui/compose/quick-guides/content/create-progress-indicator

    Sobre margem em Compose
    https://sagarbalyan.medium.com/understanding-margin-and-padding-in-compose-ui-395039a77b76
 */


@Preview(showSystemUi = true,  )
@Composable
fun PreviewMessageCard() {
    MultilanguageSupportTheme {
        Surface (modifier = Modifier.fillMaxSize().padding(top = 20.dp, start = 10.dp, end = 10.dp)){
            MessageCard(modifier = Modifier, Message("Android", body = "Jetpack Compose"))
        }
    }
}