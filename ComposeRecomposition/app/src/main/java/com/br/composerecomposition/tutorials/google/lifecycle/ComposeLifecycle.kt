package com.br.composerecomposition.tutorials.google.lifecycle

/*
    https://developer.android.com/develop/ui/compose/lifecycle



    ------------------------------------------------------------------------

    Pesquisar por: when composable is removed from composition tree ?
    - Uma composable é removida da composition tree quando nao faz mais parte
    da estrutura hierarquica da UI, isso pode ocorrer movitovado por
        - condicao logica, if/else
        - mudanca de navegacao, ou removendo um componente pai
        - Se um conteudo é provido por uma Lista e um elemento for removido

    Quando o Lifecycle é encerrado o que ocorre ?
        - onDispose: Todos os blocos DisposableEffect sao execucatos, executando seus respectivos
        onDispose

        - Coroutine Cancellation: Qualquer LaunchedEffect lancado dentro do escopo da
        composable que teve seu ciclo de vida encerrado é automaticamente cancelado

        - State Removal: Valores armazenados pela funcao remember sao limpos

        - Timing: A remoção (disposal) ocorre depois que o novo estado da UI é composto
        com sucesso, garantindo uma transicao suave

 */
