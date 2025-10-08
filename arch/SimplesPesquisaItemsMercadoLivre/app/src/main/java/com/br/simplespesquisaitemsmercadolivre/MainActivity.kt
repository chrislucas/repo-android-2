package com.br.simplespesquisaitemsmercadolivre

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
import com.br.simplespesquisaitemsmercadolivre.ui.theme.SimplesPesquisaItemsMercadoLivreTheme


/*
    API publica mercado livre
        - https://developers.mercadolivre.com.br/pt_br/itens-e-buscas
    Outroas apis interessantes
        - https://developers.mercadolivre.com.br/
        - https://developers.mercadolivre.com.br/en_us/items-and-searches
        - https://developers.mercadolivre.com.br/pt_br/gerenciamento-de-vendas

    TODO
        - implementar uma tela de pesquisa
            - A tela de pesquisa e resultado pode ser a mesma
            - Atraves de controle de estado mudar o seu comteúdo
        - Implementar uma tela de detalhes
        - Implementar uma tela de estado de erro
            - talvez nao precise de uma tela espeficica
        - Usar MVVM
        - Usar Navigation Compose
        - Criar uma solucao em um modulo que utilize MVI
        - implementar testes de Unidade em todas as camadas
            - Praticar TDD
        - criar um modulo de network, nao reutilizar o que foi feito em outros projetos
            - o objetivo aqui e medir a performance de desenvolvimento

    TODO
        - Planejar o que precisa ser feito antes escrever uma linha de código
        - Cronometrar o tempo levado para a solução completa para melhorar
        a peformace de desenvolvimento e escrita de código

    Requisitos nao funcionais
        = A primeira versao nao precisa funcionar offline
        - se nao tiver acesso a internet, mostrar uma tela de erro (estado)

     DOD
        - Ter as telas de pesquisa, detalhes e estado de erro implementadas
        - Ter 95% de cobertura de teste (nao precisa testar DTO, data class, talvez sealed class)
        - ter um repositório no github com uma boa descrição do que foi feito readme
            - Funcionalidades existentes
                - Prints ou Gifs das telas e todos os estados mapeados
            - Tecnologias utilizadas
            - Explicacao da arquitetura
                - A explicação pode ser feita através das pastas criadas
            - Alguns repos para se inspisrar
                - https://github.com/matheusjuan1/meli-android#fromHistory
                - https://github.com/LuanadeSouza/MercadoLivre/tree/master
         - A expectativa é levar 3 dias para tudo
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimplesPesquisaItemsMercadoLivreTheme {
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
    SimplesPesquisaItemsMercadoLivreTheme {
        Greeting("Android")
    }
}