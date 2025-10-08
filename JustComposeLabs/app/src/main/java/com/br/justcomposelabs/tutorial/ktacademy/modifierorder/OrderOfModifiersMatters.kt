package com.br.justcomposelabs.tutorial.ktacademy.modifierorder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.R

/*
    https://developer.android.com/develop/ui/compose/modifiers#order-modifier-matters

    A ordem das funcoes modifiers é significante. Cada funcao devolve um modifier modificado,
    a sequencia afeta o resultado final
 */

class Artist(
    val image: ImageBitmap = ImageBitmap(100, 100),
    val name: String = "",
    val lastSeenOnline: String = "1 minuto atrás"
)

@Preview(showBackground = true)
@Composable
fun OrderMatters1(artist: Artist = Artist(name = "Chris")) {
    val padding = 16.dp

    Column {

        ProfileLayout(
            modifier = Modifier.background(color = Color(0xFFE7BFBF)),
            artist = artist
        )

        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.background(
                color =  Color(0xFFB388FF)
            )
        ) {
            Box {
                Image(
                    bitmap = artist.image,
                    contentDescription = "Artist image",
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(40.dp, 40.dp)
                        .border(1.dp, color = Color.Black, shape = CircleShape)
                )

                Icon(
                    Icons.Filled.Check,
                    contentDescription = "Check mark",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(start = 23.dp, top = 22.dp)

                )
            }


            /*
                Modifier
                    .clickable(onClick = { })
                    .padding(padding)
                    .wrapContentSize()

                    Essa ordem de modifiers faz com que tod0 o componente COLUMN
                    seja clicavel inclusive a area com padding pq o modifier
                    clickable é aplicado antes
             */


            Column(
                Modifier
                    .clickable(onClick = { })
                    .padding(padding)
                    .wrapContentSize()
            ) {
                Text(artist.name)
                Text(artist.lastSeenOnline)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(
                color = Color(0xFF80D8FF)
            )
        ) {
            Image(
                bitmap = artist.image,
                contentDescription = "Artist image",
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(40.dp, 40.dp)
                    .border(1.dp, color = Color.Black, shape = CircleShape)

            )/*
                Ja a seguinte ordem

               Modifier
                    .padding(padding)
                    .clickable(onClick = {})
                    .wrapContentSize()

                A area de padding nao é clicavel
             */
            Column(
                Modifier
                    .padding(padding)
                    .clickable(onClick = {})
                    .wrapContentSize()
            ) {
                Text(artist.name)
                Text(artist.lastSeenOnline)
            }
        }
    }
}


/*
     Builtin Modifiers
     https://developer.android.com/develop/ui/compose/modifiers#built-in-modifiers
 */


@Preview()
@Composable
fun ProfileLayout(modifier: Modifier = Modifier, artist: Artist = Artist()) {
    val padding = 16.dp

    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.hero),
                contentDescription = "Artist image",
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(40.dp, 40.dp)
                    .border(1.dp, color = Color.Black, shape = CircleShape)

            )
            Icon(
                Icons.Filled.Check,
                contentDescription = "Check mark",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(start = 23.dp, top = 22.dp)

            )
        }

        Column(
            Modifier
                .clickable(onClick = { })
                .padding(padding)
                .wrapContentSize()
        ) {
            Text(artist.name)
            Text(artist.lastSeenOnline)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtistAvatar(artist: Artist = Artist()) {
    /*
        https://developer.android.com/develop/ui/compose/layouts/basics
        https://github.com/android/snippets/blob/1da1d9d645cd1a8e693981900e04d6bc32287a5c/compose/snippets/src/main/java/com/example/compose/snippets/layouts/LayoutBasicsSnippets.kt#L65-L82
     */
    Box {
        Image(
            bitmap = artist.image,
            contentDescription = "Artist image",
            modifier = Modifier.clip(shape = CircleShape)
        )
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Check mark",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(start = 30.dp, top = 25.dp)
        )
    }
}

