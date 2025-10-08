package com.br.justcomposelabs.tutorial.google.viewmodeloverview

import androidx.lifecycle.ViewModel

/*
    https://developer.android.com/topic/libraries/architecture/viewmodel

    - View Model class é uma estrutura que manter estado de logica de negocio ou de um
    componente UI a nivel de tela
        - Podemos expor estado de uma UI atraves de um componente observador como LiveData,
        Flow, StateFlow e encappsular uma logica de negocio relacionada a View

    - Beneficios de uma ViewModel
        - A alternativa para uma ViewModel é usar uma plain class que mantem o estado de um
        dado que exibimos na tela. Isso começa a ficar complicado quando navegamos entre
        activities ou entre os destinos num grafo de navegacao.

        - Usar uma classe que nao a ViewModel para isso nos força a ter que utilizar algum mecanismo
        de armazenamento dessa classe, seja onSaveInstanceState da View ou rememberSaveable do compose.

        - A ViewModel fornece uma API para persistir dados que resolve o problema

        - Pontos chave
            - Permite persistir dados para UI State
            - Prove um espaço onde podemos implementar Lógica separado da UI

    - Persistence (https://developer.android.com/topic/libraries/architecture/viewmodel#persistence)
        -

    - Ciclo de vida de uma ViewModel esta vinculado ao de um activity ou fragment ?
        - Sim, uma viewmodel persiste enquanto o componente ao qual
        esta ligado nao fo destruido permanentemente, como um activity que
        é destruida ou um fragment removido

        - A Vinculacao
            - O ciclo de vida de uma viewmodel é gerenciado por ViewModelStoreOwner
                https://developer.android.com/topic/libraries/architecture/viewmodel#scope

        - Escopo (https://developer.android.com/topic/libraries/architecture/viewmodel?hl=pt-br#scope)
            -

        - O ciclo de vida de um ViewModel (https://developer.android.com/topic/libraries/architecture/viewmodel?hl=pt-br#lifecycle)
            - O Ciclo de vida de uma viewmodel está diretamente vinculada ao escopo dela.
            - Ela permanece em memoria ate que o ViewModelStoreOwner definido como escopo dela
            desapareça
                - Isso pode ocorrer nos seguintes contextos
                    - Uma activity que finaliza
                    - Um fragment que é removido
                    - Uma entrada de uma navegacao, quando ela é removida da backstack (NavComponent)
                - Isso faz da ViewModel uma otima opcao para armazenar dados quando ocorre uma
                mudanca de configuracao


      https://developer.android.com/topic/architecture/ui-layer/stateholders


      --------------
      lifecycleowner independent compose
      https://www.linkedin.com/feed/update/urn:li:activity:7366888898473517058/

      ------------------------------------------
      what is lifecycle in android ?

      - lifecycle define e gerencia o estado dos componentes em android, tais como: Activity ou Fragment.
        - Essa gerencia ocorre desde a criacao do componente ate sua destruicao
        - Android forncece callbacks methods para interceptar esses estados
        - Como desenvolvedores, podemos utilizr esses callback methods para adicionar algum comportamento
        ou gerenciar recursos, manipular eventos como navegacao do usuario ou interrupcoes do sistema
        de forma harmoniosa.


 */

class DiceViewModel: ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }
}