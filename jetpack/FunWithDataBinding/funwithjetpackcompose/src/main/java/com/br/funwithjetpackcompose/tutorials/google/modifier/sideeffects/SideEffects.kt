package com.br.funwithjetpackcompose.tutorials.google.modifier.sideeffects

/*
    https://developer.android.com/develop/ui/compose/side-effects

    - Termo chave, Effect
        - Um efeito/effect é uma composabvle function que nao emite uma UI e causa efeito (mudanca)
        de estado quando uma composicao se completa

        - Devido a inumeras possibilidades de efeitos oferecidos no Compose, eles podem
        ser facilmente sobreutilizados
            - Temos que garantir que seu uso esteja relacionado com a UI e que nao quebre
            a regra de unidirectional data flow

            - Veja essa regra nesse documento
 */