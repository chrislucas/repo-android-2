package com.br.justcomposelabs.tutorial.google.androidview.bottomsheetdialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.br.justcomposelabs.tutorial.google.androidview.bottomsheetdialogfragment.ui.theme.JustComposeLabsTheme
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OpenModalBottomSheetActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ButtonModalBottomSheet(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ButtonModalBottomSheet(modifier: Modifier = Modifier) {
    val modalBottomSheet = ComposeViewBottomSheet()
    val context = LocalContext.current
    if (context is FragmentActivity) {
        val fragmentManager = context.supportFragmentManager
        Box(modifier = modifier.padding(16.dp)) {
            Button(onClick = {
                modalBottomSheet.show(fragmentManager, ComposeViewBottomSheet.TAG)
            }) {
                Text("Open ModalBottomSheet")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustComposeLabsTheme {
        ButtonModalBottomSheet(modifier = Modifier.fillMaxSize())
    }
}

class ComposeViewBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button({ dismiss() }) {
                        Text("dismiss")
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "ComposeViewBottomSheet"
    }
}