package com.br.inventoryroomdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.inventoryroomdb.ui.theme.InventoryRoomDBTheme

/*
    Room
    https://developer.android.com/jetpack/androidx/releases/room
    Save data in a local database using Room
    https://developer.android.com/training/data-storage/room
 */
/*
    https://developer.android.com/codelabs/basic-android-kotlin-compose-update-data-room#0
    https://github.com/google-developer-training/basic-android-kotlin-compose-training-inventory-app
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InventoryRoomDBTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InventoryRoomDBTheme {
        Greeting("Android")
    }
}
