package com.br.justcomposelabs.tutorial.medium.multiplespreviews

import androidx.compose.ui.tooling.preview.Preview

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Portrait",
    device = "spec:width=411dp,height=891dp,dpi=420,orientation=portrait"
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Landscape",
    device = "spec:width=411dp,height=891dp,dpi=420,orientation=landscape"
)
annotation class MultiDeviceOrientationPreviews
