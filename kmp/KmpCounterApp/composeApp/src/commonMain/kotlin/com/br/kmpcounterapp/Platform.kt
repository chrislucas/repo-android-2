package com.br.kmpcounterapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
