package com.br.composerecomposition.tutorials.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun currentHour() = SimpleDateFormat(
    "HH:mm:ss",
    Locale.getDefault()
).format(Date())
