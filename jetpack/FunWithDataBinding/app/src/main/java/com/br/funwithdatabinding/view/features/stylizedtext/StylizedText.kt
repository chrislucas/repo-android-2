package com.br.funwithdatabinding.view.features.stylizedtext

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.br.funwithdatabinding.R


/*
    TODO
        Estilizar uma String com os dados de StylizedText
 */
data class StylizedText(
    val content: String,
    val isBold: Boolean,
    val isItalic: Boolean,
    val textColor: String,
    val size: String? = null,
    val key: String? = null,
    var values: List<StylizedText>? = null,
    val fontWeight: String? = null,
    val lineSpacing: Int = 0
) {
    val color: Int
        get() = TextColor.get(textColor).color
}


enum class TextColor(val color: Int) {
    BLACK(R.color.black), YELLOW(R.color.yellow);

    companion object {
        fun get(textColor: String) = TextColor.entries.find { textColor == it.name } ?: BLACK
    }
}

class StylizedTextView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
): AppCompatTextView(context, attr) {

}