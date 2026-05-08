package com.br.justcomposelabs.tutorial.google.compose.sideffects.rememberupdatedstate.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.br.justcomposelabs.utils.composable.fillMaxSizePadding

@Composable
fun ProfileScreen(profile: Profile) {
    Column(modifier = Modifier.fillMaxSizePadding()) {
        Text(
            text = profile.name,
            style = TextStyle(
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontStyle = MaterialTheme.typography.headlineSmall.fontStyle,
                fontWeight = MaterialTheme.typography.headlineSmall.fontWeight
            ),
            maxLines = 1
        )
        Text(
            text = "${profile.age}",
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
            )
        )
    }
}