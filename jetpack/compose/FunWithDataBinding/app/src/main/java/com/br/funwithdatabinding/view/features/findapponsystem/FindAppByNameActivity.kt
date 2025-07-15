package com.br.funwithdatabinding.view.features.findapponsystem

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.br.funwithdatabinding.view.features.findapponsystem.ui.theme.FunWithDataBindingTheme

class FindAppByNameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShowInstalledApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ShowInstalledApp(modifier: Modifier = Modifier) {
    val installedApss = LocalContext.current.packageManager.installedApps()

    val text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    /*
        TextFieldValue
        https://developer.android.com/reference/kotlin/androidx/compose/ui/text/input/TextFieldValue
     */

    Column(modifier = modifier) {
        OutlinedTextField(
            value = text,
            onValueChange = { text ->

            },
            label = {
                Text(text ="label")
            }
        )

        LazyRow() {
            items(installedApss.size) { idx ->
                Text(text = "${installedApss[idx]}")
            }
        }
    }

}

@Preview(showBackground = true, name = "Show Installed App")
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
        ShowInstalledApp(Modifier.fillMaxSize())
    }
}


private fun PackageManager.installedApps() = getInstalledApplications(PackageManager.GET_META_DATA)