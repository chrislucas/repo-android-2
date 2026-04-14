package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme
import java.util.Locale


private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100
private const val InactiveTabOpacity = 0.60f


@Composable
internal fun RallyTab(
    text: String,
    icon: ImageVector,
    onSelected: () -> Unit,
    selected: Boolean
) {
    val color = MaterialTheme.colorScheme.onSurface
    val durationMillis = if (selected) {
        TabFadeInAnimationDuration
    } else {
        TabFadeOutAnimationDuration
    }

    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }

    val tabColor by animateColorAsState(
        targetValue = if (selected) color else color.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec
    )

    Row(
        modifier = Modifier.padding(16.dp)
            .animateContentSize()
            .height(TabHeight)
            .selectable(
                selected = selected,
                onClick = onSelected,
                /*
                    Semantics
                    https://developer.android.com/develop/ui/compose/accessibility/semantics

                    Role: classe da api Semantics
                    https://developer.android.com/reference/kotlin/androidx/compose/ui/semantics/Role

                 */
                role = Role.Tab
            )
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = tabColor)
        if (selected) {
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = text.uppercase(Locale.getDefault()), color = tabColor)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RallyTabSelectedPreview() {
    JustComposeLabsTheme {
        Box(
            modifier = Modifier
                .systemBarsPadding()
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            RallyTab(
                text = "Overview",
                icon = Icons.Filled.PieChart,
                onSelected = {},
                selected = true
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RallyTabUnselectedPreview() {
    JustComposeLabsTheme {
        Box(
            modifier = Modifier
                .systemBarsPadding()
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            RallyTab(
                text = "Overview",
                icon = Icons.Filled.PieChart,
                onSelected = {},
                selected = false
            )
        }
    }
}