package com.br.experience.basic.codelabs

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.experience.basic.ThinkingInCompose
import com.br.experience.basic.recomposition.experiment.RecompositionExperimentActivity
import com.br.experience.basic.recomposition.p1.PlayWithRecompositionI
import com.br.experience.basic.ui.theme.BasicTheme

/**
 * https://developer.android.com/jetpack/compose/mental-model#recomposition
 * Compose is a modern declarative UI Toolkit for Andorid.
 *
 * State in compose
 * https://developer.android.com/codelabs/jetpack-compose-basics#6
 *
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicTheme {
                // A surface container using the 'background' color from the theme
                NamesInColumn()
            }
        }
    }
}

@Composable
private fun Default() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Greeting("Android")
    }
}

@Composable
private fun OpenThinkingCompose() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.background
    ) {
        val ctx = LocalContext.current
        Button(onClick = {
            ctx.startActivity(Intent(ctx, ThinkingInCompose::class.java))
        }) {
            Text(text = "Open ThinkingInCompose Activity")
        }
    }
}

@Composable
private fun OpenActivity(title: String, cls: Class<out ComponentActivity>) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.background
    ) {
        val ctx = LocalContext.current
        Button(onClick = {
            ctx.startActivity(Intent(ctx, cls))
        }) {
            Text(text = title)
        }
    }
}

/**
 * Reusing composables
 * https://developer.android.com/codelabs/jetpack-compose-basics#4
 *
 * Criando um componente para que ele possa ser reaproveitado e o codigo
 * fique mais simples
 */

@Composable
private fun GreetingContainer() {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        Greeting(name = "chrisluccas !!!")
    }
}

@Composable
private fun NamesInColumn(names: List<String> = listOf("World", "Compose")) {
    Surface(color = MaterialTheme.colors.background) {
        Column {
            names.forEach { name ->
                Greeting(name = name)
            }
            OpenActivity("Open ThinkingInCompose Activity", ThinkingInCompose::class.java)
            OpenActivity("Open ListWithHeader Activity", ListWithHeader::class.java)
            OpenActivity("Open PlayWithRecompositionI Activity", PlayWithRecompositionI::class.java)
            OpenActivity("Open RecompositionExperimentActivity Activity", RecompositionExperimentActivity::class.java)
        }
    }
}

@Composable
private fun NamesInRow(names: List<String> = listOf("World", "Compose")) {
    Surface(color = MaterialTheme.colors.background) {
        Row {
            names.forEach { name ->
                Greeting(name = name)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Surface(color = MaterialTheme.colors.primary) {

        /**
         * State
         * https://developer.android.com/codelabs/jetpack-compose-basics#6
         *
         * Usar somente uma variavel booleana nao funciona muito bem. Pq?
         *
         * Apps compose transformam dados em uma UI ao chamar composable
         * functions. Se o dado muda, o compose re-executa a(s) funcao(oes)
         * com o novo dado,
         *
         */

        val expanded = remember { mutableStateOf(false) }

        /**
         * Modifiers
         * https://developer.android.com/codelabs/jetpack-compose-basics#3
         * Modifiers dizem como um elemento UI deve ser disposto, mostrato ou
         * como deve se comportar dentro do seu componente pai
         *
         * Lista de modificadores
         * https://developer.android.com/jetpack/compose/modifiers-list
         */
        val ctx = LocalContext.current
        val modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 12.dp)
            //.fillMaxWidth()
            .clickable {
                Toast
                    .makeText(ctx, "Clicked", Toast.LENGTH_LONG)
                    .show()
            }
        Row(modifier) {
            Column(Modifier.weight(1f)) {
                Text(text = "Hello, ")
                Text(text = "$name")
            }
            OutlinedButton(onClick = {
                expanded.value = !expanded.value
            }) {
                Text(text = if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun ListStringInRow(names: List<String> = listOf("World", "Compose")) {
    Column {
        val modifier = Modifier
            .padding(vertical = 32.dp, horizontal = 8.dp)
            .fillMaxWidth()
        names.forEach { name ->
            Row(modifier) {
                Text(text = name)
            }
        }
    }
}

@Composable
fun ListStringInColumn(names: List<String> = listOf("World", "Compose")) {
    Row {
        val modifier = Modifier.padding(32.dp)
        names.forEach { name ->
            Column(modifier) {
                Text(text = name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BasicTheme {
        //NamesInRow()
        NamesInColumn()
        //ListStringInRow()
        //ListStringInColumn()
    }
}