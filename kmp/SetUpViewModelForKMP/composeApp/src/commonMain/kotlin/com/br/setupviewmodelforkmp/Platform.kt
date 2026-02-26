package com.br.setupviewmodelforkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform