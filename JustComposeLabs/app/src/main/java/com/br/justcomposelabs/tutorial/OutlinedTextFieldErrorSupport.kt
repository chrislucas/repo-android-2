package com.br.justcomposelabs.tutorial

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.justcomposelabs.utils.composable.paddingEdgeToEdge
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*
    TODO utilizar essa view model na funcao OutlinedTextFieldErrorSupport
 */
class TextFieldStateViewModel : ViewModel() {
    private val mutableContent = MutableStateFlow("")
    val content: StateFlow<String> = mutableContent.asStateFlow()

    private val mutableStateError = MutableStateFlow(false)
    val stateError = mutableStateError.asStateFlow()

    fun onTextChange(content: String) {
        mutableContent.update { content }
    }

    fun onEventError(isError: Boolean) {
        mutableStateError.update { isError }
    }
}

sealed class StatusTextValidation {
    object Success : StatusTextValidation()

    object Error : StatusTextValidation()

    object Warning : StatusTextValidation()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StatefulOutlinedTextFieldErrorSupport(modifier: Modifier = Modifier) {
    var content by remember { mutableStateOf("") }

    var isError by remember { mutableStateOf(false) }

    Column(
        modifier =
        modifier
            .statusBarsPadding()
            .paddingEdgeToEdge()
            .navigationBarsPadding()
            .fillMaxSize(),
    ) {
        OutlinedTextField(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, end = 2.dp),
            value = content,
            onValueChange = {
                content = it
                isError = it.isEmpty()
            },
            label = { Text("Digite um valor") },
            placeholder = {
                Text("Digite um valor")
            },
            isError = isError,
            /*
                Uma função composable, geralmente um Text, mostrado
                abaixo do componente OutlinedTextField.
                Essa função composable é geralmente executada a partir de uma
                condição booleana
             */
            supportingText = {
                if (isError) {
                    Text(
                        text = "Error",
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            },
            /*
                Ícone opcional mostrado no final do campo de texto
             */
            trailingIcon = {
                if (isError) {
                    Icon(
                        Icons.Filled.Warning,
                        "error",
                        tint = MaterialTheme.colorScheme.error,
                    )
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )
    }
}

/*
    Criar um componente de TextField que suporte mensagem de erro
        - Criar estados de erros diferentes para poder suportar trailingIcon diferentes
            - error
            - warning
            - success
        - Problema: para cada estado após validação do texto, executar uma composable
            - Talvez usar o padrão Command seja uma boa solucao
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StatelessOutlinedTextFieldErrorSupport(
    modifier: Modifier = Modifier,
    viewModel: TextFieldStateViewModel = viewModel(),
) {
    Column(
        modifier =
        modifier
            .statusBarsPadding()
            .paddingEdgeToEdge()
            .navigationBarsPadding()
            .fillMaxSize(),
    ) {
        Text("Ola, mundo!")
    }
}

interface ValidationText {
    fun valid(content: String): Boolean
}

@JvmInline
value class NonEmptyText private constructor(
    val content: String,
) {
    companion object {
        fun of(content: String) =
            if (valid(content)) {
                NonEmptyText(content)
            } else {
                null
            }

        private fun valid(content: String) = content.isNotEmpty() && content.isNotBlank()
    }
}

/*
    Checar se a palavra nao é uma palavra proibida
 */
@JvmInline
value class NonForbidden private constructor(
    val word: String,
) {
    companion object {
        fun of(word: String) = NonForbidden(word)
    }
}
