package com.br.funwithjetpackcompose.tutorials.google.interoperabillity.fragmentsincompose

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
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.br.funwithjetpackcompose.tutorials.google.interoperabillity.fragmentsincompose.fragment.CustomFragment
import com.br.funwithjetpackcompose.tutorials.google.interoperabillity.fragmentsincompose.ui.theme.FunWithDataBindingTheme
import com.br.mylibrary.databinding.FragmentCustomBinding


/*
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/views-in-compose

    FragmentContainerView
    https://developer.android.com/reference/androidx/fragment/app/FragmentContainerView

    Criar um Fragment
    https://developer.android.com/guide/fragments/create?hl=pt-br

    What is FragmentContainerView? Its benefits over FrameLayout.
    https://sonikarsh.medium.com/what-is-fragmentcontainerview-its-benefits-over-framelayout-8abe8598fcc5

 */
class FragmentsInComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FragmentInCompose(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun FragmentInCompose(modifier: Modifier = Modifier) {
    AndroidViewBinding(FragmentCustomBinding::inflate) {
        val fragment = customFragmentContainerView.getFragment<CustomFragment>()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
        FragmentInCompose()
    }
}