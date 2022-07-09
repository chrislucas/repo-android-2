package com.br.experience.funmobdatascience.utils

import java.io.File
import java.io.FileInputStream
import java.io.IOException

private fun basePathProject(): String {
    val path = "./app/src/test/assets/mocks/"
    val contains = File("./").list()?.contains("app") ?: false
    return if (contains) {
        path
    } else {
        "../${path}"
    }
}

fun readFromAssetsFolder(path: String): String {
    val newPath = String.format("%s/%s", basePathProject(), path)
    return try {
        val stream = FileInputStream(File(newPath))
        val buffer = ByteArray(stream.available())
        stream.read(buffer)
        stream.close()
        String(buffer)
    } catch (e: IOException) {
        ""
    }
}