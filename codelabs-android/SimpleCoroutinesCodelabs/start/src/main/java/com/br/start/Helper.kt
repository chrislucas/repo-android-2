package com.br.start


// Exemplo de teste
fun <T, R> T?.letOrElse(exec: T.() -> R, orElse: () -> R): R {
    return if (this == null) {
        orElse()
    } else {
        exec()
    }
}