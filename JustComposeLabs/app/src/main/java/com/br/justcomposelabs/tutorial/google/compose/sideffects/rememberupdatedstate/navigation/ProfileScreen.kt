package com.br.justcomposelabs.tutorial.google.compose.sideffects.rememberupdatedstate.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.br.justcomposelabs.utils.composable.fillMaxSizePadding
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.tutorial.compose.layoutmodifier.pad
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

@Composable
fun ProfileScreen(profile: Profile) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            text = "Name: ${profile.name}",
            style = TextStyle(
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontStyle = MaterialTheme.typography.headlineSmall.fontStyle,
                fontWeight = MaterialTheme.typography.headlineSmall.fontWeight
            ),
            maxLines = 1
        )
        Text(
            text = "Age: ${profile.age}",
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    JustComposeLabsTheme {
        ProfileScreen(
            profile = Profile(
                name = "John Doe",
                age = 30
            )
        )
    }
}
