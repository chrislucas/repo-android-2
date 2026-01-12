package com.br.justcomposelabs.tutorial.google.image.loading.coil

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.br.justcomposelabs.R


data class ImageContext(val model: Any, val contentDescription: String = "")
@Composable
fun CardImageComponent(
    modifier: Modifier = Modifier,
    imageContext: ImageContext = ImageContext(model = R.drawable.oreo)
) {
    /*
        https://developer.android.com/develop/ui/compose/quick-guides/content/create-card-as-container
     */
    ElevatedCard (
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            AsyncImage(
                model = imageContext.model,
                contentDescription = imageContext.contentDescription,
                error = painterResource(R.drawable.bakery_back)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardImageComponentPreview(
    @PreviewParameter(ImageContextProvider::class) imageContext: ImageContext
) {
    /*
        https://developer.android.com/develop/ui/compose/tooling/previews#preview-data
     */
    CardImageComponent(
        imageContext = imageContext
    )
}

class ImageContextProvider : PreviewParameterProvider<ImageContext> {
    override val values: Sequence<ImageContext>
        get() = sequenceOf(
            ImageContext(
                R.drawable.honeycomb,
                "Android Logo"
            ),
            ImageContext(
                "https://www.gstatic.com/devrel-devsite/prod/v9de0f443992d54ad799f913d3a82969ff613e39ab9e3e056983c556b3c2ef1f8/android/images/lockup.png",
                "Android Logo"
            ),
            ImageContext(
                "https://fastly.picsum.photos/id/572/200/300.jpg?hmac=Rt4zD8IxoA-nMVDrBQ72mgbTVRfQ6OwW3MhWy_3lpdk",
                "Android Logo"
            ),
            ImageContext(
                "https://picsum.photos/id/1/200/300",

            )
        )

}