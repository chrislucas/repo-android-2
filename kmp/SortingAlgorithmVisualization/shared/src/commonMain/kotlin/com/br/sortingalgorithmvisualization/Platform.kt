package com.br.sortingalgorithmvisualization

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
