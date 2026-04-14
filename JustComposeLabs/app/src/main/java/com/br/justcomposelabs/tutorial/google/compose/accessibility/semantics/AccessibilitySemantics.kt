package com.br.justcomposelabs.tutorial.google.compose.accessibility.semantics

/*
    https://developer.android.com/develop/ui/compose/accessibility/semantics

    - "Information about the meaning and role of a component in Compose is called semantics,"
        _ Uma maneira de ter um contexto adicional sobre composables que pode ser útil para serviços como
        Acessibilidade, autofill ou teste

        - Exemplo
            - Um icone de uma camera pode ser visualmente somente uma imagem, mas semanticamente pode significar
            um recurso para tirar foto

        - Combinacao de semantica com apis apropriadas de Compose, provemos mais informacao sobre o componente para
        serviços por exemplo de acessibilidade, que decden como representa-lo para o usuário

        - As apis de Material, Compose UI e Foundation possuem semântica integrada com suas funcoes especficicas,
        entretanto é possível modifica-la  por apis existentes ou definir novos componentes personalizados

     - Semantic properties
        - https://developer.android.com/develop/ui/compose/accessibility/semantics#properties
        - Propriedade semantic tgransminie o significado da correspondente composable
            - Text composable possui a propriedade sementica text (porque esse é o significado de Text)
            - Icon contém a propriedade contentDescription que transmite em texto o que o Icone
            significa
 */