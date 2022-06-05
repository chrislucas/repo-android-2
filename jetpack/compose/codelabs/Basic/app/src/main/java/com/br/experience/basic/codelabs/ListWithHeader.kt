package com.br.experience.basic.codelabs

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.br.experience.basic.ui.theme.BasicTheme

/**
 * https://developer.android.com/jetpack/compose/mental-model#recomposition
 */

class ListWithHeader : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicTheme {
                ImplNamePicker()
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun NamePicker(header: String, names: List<String>, onNameClicked: (String) -> Unit) {
    Column {
        /**
         * Isso ira ser recomposoto quando o header mudar, mas nao quando os names mudarem
         */
        Text(text = header, style = MaterialTheme.typography.h5)
        Divider()
        /**
         * LayzColum é o RecyclerView do Compose
         * A funcao lambda passada para funcao items() é similtar ao RecyclerView.ViewHolder
         */
        LazyColumn {
            items(names) { name ->
                NamePickerItem(name, onNameClicked)
            }
        }
    }
}

@Composable
private fun NamePickerItem(name: String, onClicked: (String) -> Unit) {
    Text(name, Modifier.clickable { onClicked(name) }.fillMaxWidth())
}

@Composable
private fun ImplNamePicker() {
    val ctx = LocalContext.current
    NamePicker(
        header = "Title", names = listOf(
            "chris",
            "antonio",
            "fernandes",
            "almeuda",
            "maria",
            "juliana"
        )
    ) { name ->
        Toast.makeText(ctx, name, Toast.LENGTH_SHORT).show()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    BasicTheme {
        ImplNamePicker()
    }
}