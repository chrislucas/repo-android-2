package com.br.justcomposelabs

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

/*
    TODO transformar essa classe em algo util :)
 */

class GenericScope(
    private val scope: CoroutineScope = CoroutineScope(EmptyCoroutineContext),
    private val execute: suspend () -> Unit
) {

    /*
        https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/

        CoroutineScope:

           - Define um scope para uma nova coroutine. Toda a coroutine builder (launch, async, etc), é uma
           extensao da interface CoroutineScope e herda o atributo coroutineContext dessa mesma interface
           para aitoamaticamente propagar todos seus elementos
           (public interface Element : kotlin.coroutines.CoroutineContext) e cancelamento.

           - As memores formas de obter uma instancia de um scope sao atraves das factories functions
               - CoroutineScope()
               - MainScope()
               - Armazenando a instancia do scope podemos interromper a execucao de todas as corouines
               que estao sendo executadas nele.

               - Adicionalmente Context Elements podem ser adicionados ao escopo atraves do operador
               + (plus)


           Convention for structured concurrency

           - nao é recomendado a implementacao "manual" dessa interface, implementacao
           por delegation
                  - leitura introdutorio para compreender structured concurrency em kotlin
                    Introduction to Structured Concurrency: CoroutineScope & CoroutineContext
                    https://medium.com/coding-kinetics/introduction-to-structured-concurrency-coroutinescope-coroutinecontext-50103363c611
           - Por convencao, o contexto de um escopo deve conter uma instancia de um Job para forcar
           a disciplina de structured concurrency com propagacao de cancelamento.

           - Toda coroutine builder (launch, async, etc) and toda scoping function (coroutineScope, withContext)
           prove seu proprio scopo com sua propria instancia de Job dentro do inner block.

           - Por convencao essas funcoes esperam todas as coroutines dentro do seu bloco de codigo
           terminarem antes de completarem sua execucao, forcando assim a disciplina de structured concurrency.
               - Mais detalhes na documentacao de Job:
     */

    fun destroy() {
        scope.cancel()
    }

    fun run() = scope.launch {
        execute.invoke()
    }
}