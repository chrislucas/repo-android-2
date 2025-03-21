package com.br.kmm.mystuffs

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform