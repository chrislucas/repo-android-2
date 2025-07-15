package com.br.kmm.multilanguagesupport.tutorials.google.tutorials.lessonii

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.kmm.multilanguagesupport.R

data class Message(val author: String, val body: String)

/*
    https://developer.android.com/develop/ui/compose/tutorial
    DONE
 */

@Preview(
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = false
)
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = false
)
@Composable
fun MessageCard(
    modifier: Modifier = Modifier,
    msg: Message = Message("Android", "Jetpack Compose")
) {
    Row(modifier = modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(
                    1.5.dp,
                    MaterialTheme.colorScheme.secondary
                )
        )

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }

        Column (modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.width(4.dp))

            /*
                Shape:
                Com shape conseguimos personalizar o corpo
                da mensagem e sua elevacao. Podemos adicionar
                padding tbm
             */

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 3.dp,
                color = Color(0xFF8C9EFF),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 5.dp),
                    color = Color.White,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@Preview(
    name = "Previews Firts",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = false
)
@Composable
fun PreviewMessageCard() {
    /*
        Surface
        https://composables.com/material/surface
     */
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        MessageCard(
            modifier = Modifier.fillMaxWidth(),
            msg = Message("Android", body = "Jetpack Compose")
        )
    }
}