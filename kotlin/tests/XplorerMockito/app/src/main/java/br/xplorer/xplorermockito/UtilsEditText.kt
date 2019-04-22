package br.xplorer.xplorermockito

import android.widget.EditText

fun EditText.content() : String = text.toString()

fun EditText.isEmpty() : Boolean = content().isEmpty() || content().matches(Regex("\\s+"))