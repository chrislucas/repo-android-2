package com.br.experience.funmobdatascience.utils

import com.google.gson.Gson

inline fun <reified T> Gson.fromJsonToObject(path: String) : T =
    fromJson(readFromAssetsFolder(path), T::class.java)