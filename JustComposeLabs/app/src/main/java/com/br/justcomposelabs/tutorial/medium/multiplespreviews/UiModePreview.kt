package com.br.justcomposelabs.tutorial.medium.multiplespreviews

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "High Density / Large Font",
    device = "spec:width=411dp,height=891dp,dpi=480",
    fontScale = 1.2f,
)
@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "High Density / Large Font",
    device = "spec:width=411dp,height=891dp,dpi=480",
    fontScale = 1.2f,
)
@Preview(
    name = "HDPI NIGHT MODE OFF",
    group = "DPI",
    device = "spec:width=360dp,height=640dp,dpi=240",
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "HDPI NIGHT MODE ON",
    group = "DPI",
    device = "spec:width=360dp,height=640dp,dpi=240",
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "XHDPI NIGHT MODE ON",
    group = "DPI",
    device = "spec:width=360dp,height=640dp,dpi=320",
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "XHDPI NIGHT MODE OFF",
    group = "DPI",
    device = "spec:width=360dp,height=640dp,dpi=320",
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "XXHDPI NIGHT MODE YES",
    group = "DPI",
    device = "spec:width=360dp,height=640dp,dpi=480",
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "XXHDPI NIGHT MODE OFF",
    group = "DPI",
    device = "spec:width=360dp,height=640dp,dpi=480",
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "XXXHDPI NIGHT MODE YES",
    group = "DPI",
    device = "spec:width=360dp,height=640dp,dpi=640",
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "XXXHDPI NIGHT MODE NO",
    group = "DPI",
    device = "spec:width=360dp,height=640dp,dpi=640",
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_NO
)
annotation class UiModePreview
