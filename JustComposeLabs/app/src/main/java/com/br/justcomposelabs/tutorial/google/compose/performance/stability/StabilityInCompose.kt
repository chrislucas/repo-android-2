package com.br.justcomposelabs.tutorial.google.compose.performance.stability

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

/*
    Stability in Compose
    https://developer.android.com/develop/ui/compose/performance/stability

    - Compose considera tipos para serem "estaveis" ou "instaveis".
        - Um tipo é estavel se ele é imutavel ou se for possivel para o Compose
        saber se o valor mudou entre recomposicoes.

        - UM tipo ;é instavel se o Compose nao pode saber se o valor mudou entre
        recomposicoes

        COmpose usa a estabilidade de parametros de uma funcao composable para determinar
        se pode pular a funcao durante a recomposicao

            - Stable parameter:
                Se a funcao composable tem parametros estaveis que nao tiveram mudanca, o Compose
                pula a funcao
            - Se a composable tem parametros instavel, Compose sempre recomponhem a funcao quando
            recomponhem o componente pai.



        - Podemos observar problemas de performance se o app possuir muitaas funcoes composable com tipos
        instaveis

        Como aumentar a estabilidade do app ?

        - Immutable Objects
            - https://developer.android.com/develop/ui/compose/performance/stability#immutable-objects
            - data class Contact(val name: String, val number: String)

        - Mutable Object
            - data class Contact(var name: String, var number: String)
            - o exemplo acima é mutavel, se uma propriedade mudar o Compose nao estara ciente.
            Isso porque compose somente rastrea mudancas em Composer State Objects
            - State Objects / Mutable State: https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState
            - Compose considera essa classe instavel e nao ignora recomposicoes em classes instaveis.
            - Se a classe Contact for definida de maneira mutavel, a funcao ContactRow poderia sofrer
            recomposicao a qualquer momento que selected mudar


      Funcoes: https://developer.android.com/develop/ui/compose/performance/stability#functions
      -  Compose pode marcar funcoes como skippable ou restartable. Note que a funcao
      pode ser marcado como um, outro, ambos ou nenhum.

        - skippable: Se o compilador marcar a composable como skippable, Compose pode "pular"
        a funcao durante recomposition se todos os argumentos ssao  iguais aos seus valores
        anteriores

        - restartable: Uma funcao composable que é restartable serve como "scope" onde
        recomposition pode iniciar.
            - Isso significa que a funcao pode ser ponto de entrada por onde Compose inicia
            a re-execucao do codigo para recomposicao apos mudanca do estado.

      Tipos: https://developer.android.com/develop/ui/compose/performance/stability#types
      - Compose marca tipos como Immutable ou Stable, cada tipo pode ser um ou outro
        - Immutable:
            Comopse marca um tipo como Immutable se o tipo for garantidamente imutavel.
            - Exemplo: data class Contact(val name: String, val number: String)
            - seus valores depois de criados nao podem ser alterados
        - Stable:
            - Indica um tipo cujas propriedades podem mudar depois de sua construcao. Se e quando
            essas propriedades mudarem durante o tempo de execucao, Compose tonar-se ciente
            dessa mudancas

        - Nota
            - A os parametros de uma composable nao precisam ser Immutable para serem consideradas
            skippable.
            - Pode ser impraticavel manter contratos somente com estruturas imutaveis, por
            isso Compose prove mutables classes que mantem contratos dessa natureza para ajudar
                - MutableState
                - SnapshotStateMap
                - SnapshotStateList

      Debug Stability
      - Se o app realiza recomposicoes em composables cujos parametros
      nao mudaram, primeiro check se os parametros sao mutaveis, Compose sempre recomponhem
      componentes se passamos um tipo com a propriedade var, ou a uma propriedade val que usa
      um tipo instavel conhecido

      - Diagnose stability issues
      https://developer.android.com/develop/ui/compose/performance/stability/diagnose

      - Jetpack Compose Performance
      https://developer.android.com/develop/ui/compose/performance

      - Summing Up
        - Parameters: Compose determina a ESTABILIDADE dde cada parametro da funcao composable
        para determinar quais composables devem ser ignoradas durante a recomposicao

        - Immediate Fixes: Se notarmos que a funcao composable nao esta sendo ignorada e isso
        causa uma perda de desempenho, devemos comecar a analise pela causa
        mais simples de instabilidade que é parametros com var

        - Compiler Reports:
            - podemos usar reports do compilador para deerminar qual nivel de estabilidade
            esta sendo inferido nas classes do projeto

            - https://developer.android.com/develop/ui/compose/performance/stability/diagnose

       - Collections
            - Compose sempre considera classes de colecao unstable (List, Set, Map).
            - Isso porque Compose nao pode garantir que elas sao imutaveis
            - Podemos usar kotlin immutable collections ao inves de usar anotacoes como @Immutable ou @Stable
                - https://developer.android.com/develop/ui/compose/performance/stability/fix#immutable-collections

       - Other Modules
         -  Compose sempre considera instavel as classes provientes de modulos onde o compilador
         Compose nao executa.

      TODO
      - Ler esse artigo
      - https://medium.com/androiddevelopers/jetpack-compose-stability-explained-79c10db270c8
 */

data class Contact(
    val name: String,
    val number: String,
)

// https://developer.android.com/develop/ui/compose/tooling/previews#preview-data
class ContactParameterProvider : PreviewParameterProvider<Contact> {
    override val values: Sequence<Contact>
        get() =
            sequenceOf(
                Contact(name = "John", number = "1122223344"),
                Contact(name = "Mary", number = "23111118888"),
                Contact(name = "Fabian", number = "222224434"),
            )
}

@Preview(showBackground = true)
@Composable
fun ContactRow(
    @PreviewParameter(ContactParameterProvider::class) contact: Contact,
    modifier: Modifier = Modifier,
) {
    var selected by remember { mutableStateOf(true) }

    Row(modifier = modifier) {
        if (selected) {
            ContactDetails(Modifier.weight(0.5f), contact)
        }
        ToggleButton(Modifier.weight(0.5f), selected, { selected = !selected })
    }
}

@Composable
fun ToggleButton(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onToggled: (Boolean) -> Unit,
) {
    var checked by remember { mutableStateOf(selected) }
    Switch(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onToggled,
    )
}

@Composable
fun ContactDetails(
    modifier: Modifier = Modifier,
    contact: Contact,
) {
    Column(modifier = modifier) {
        Text(text = "Name: ${contact.name}")
        Text(text = "Number: ${contact.number}")
    }
}

/*
    https://proandroiddev.com/stability-in-jetpack-compose-explained-87e78e0ae20e
 */
