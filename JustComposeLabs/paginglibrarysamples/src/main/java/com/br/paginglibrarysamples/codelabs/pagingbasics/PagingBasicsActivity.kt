package com.br.paginglibrarysamples.codelabs.pagingbasics

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
import com.br.paginglibrarysamples.codelabs.pagingbasics.ui.theme.JustComposeLabsTheme

/*
    Overview
    https://developer.android.com/topic/libraries/architecture/paging/v3-overview#kts

    - A paging library ajuda a carregar e mostrar conjunto de dados de um longo
    dataset a partir de um storage local ou network.

    - Com essa lib podemo usar fonte de dados seja via network ou outros recursos de
    dados de maneira mais eficiente.

    - Esse componente é projetado para se encaixar nos componentes de arquitetura
    recomendada pela google. Integra claramente com os demains componentes Jetpack

    Benefifios de usar Paging Library

    - In-memory caching para paged data. Isso ajuda garantir que seu app use um sistema de recursos / fonte de dados
    eficientemente enquato trabalha

    - Possui uma implementacao de deduplicacao permitindo garantir o usod de rede e system resources
    de forma eficiente

    - Adaptaders de reyclerview configuraveis que automaticamente realizam request de dados conforme
    o usuario realiza scroll em direcao aos final dos dados carregados


    - implementacao de error handling, incluindo funcionalidades como refresh e retry

    Arquitetura da biblioteca
    https://developer.android.com/topic/libraries/architecture/paging/v3-overview#architecture

    - Coponente operam em 3 camadas
        - Repository, Viewmodel e UI
            - PagingSource e RemoteMediator encontram-se na Repository
            - Pager e Flow<PagingData> na Viewlmode
            - PagingDataAdapter na UI
        - Repositoru Layer
            - PagingSource: Cada PagingSource defina a fonte de dados e como retornar os dados a
            partir dela. Um objeto PagingSource pode carregar dados de qualquer fonte incluindo netowkr
            ou base de dados

            - RemoteMediator: Outr componetne da biblioteca Paging. Eke manipula a paginacao a partir
            da camada de fonte de dados




    ------------------------------------------------------------------------------------------------

    https://developer.android.com/codelabs/android-paging-basics#0
 */
class PagingBasicsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
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
    JustComposeLabsTheme {
        Greeting("Android")
    }
}