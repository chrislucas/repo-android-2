package com.br.justcomposelabs.tutorial.google.compose.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/*
    https://developer.android.com/develop/ui/compose/components/dialog#dialog-composable
    - Dialog é um composable basico que nao prove nenhum estilo ou "espaços/lugares" predefinidos
    para conteúdo, como botões, icones, título, texto, como AlertDialog

    - É um container simples que devemos preencher com outros componentes ou containers como um Card

    - Dialog Properties
        - onDismissRequest = () -> Unit
            - funcao lambda chamada quando a caixa de dialogo é fechada
        - properties: DialogProperties
            - DialogProperties que prove informacoes para personalizacao do componente
        - content: @Composable () -> Unit,
 */


@Preview(showBackground = true)
@Composable
fun MinimalDialog() {
    /*
        Diferente da AlertDialog aqui precisamos manualmente especificar a dimensao
        e o formato do componente, precisamos também prover um container interno
     */

    var showDialog by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { showDialog = !showDialog }) {
            androidx.compose.material3.Text("Open Dialog")
        }
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = ! showDialog},
            properties = DialogProperties()
        ) {
            Card(
                modifier = Modifier.fillMaxWidth()
                    .height(200.dp)
                    .width(16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "This is a minimal dialog",
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }

}