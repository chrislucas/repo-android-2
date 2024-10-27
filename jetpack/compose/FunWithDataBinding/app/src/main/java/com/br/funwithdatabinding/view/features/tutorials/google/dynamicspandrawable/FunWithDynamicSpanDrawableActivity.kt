package com.br.funwithdatabinding.view.features.tutorials.google.dynamicspandrawable

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.DynamicDrawableSpan
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R
import com.br.funwithdatabinding.databinding.ActivityFunWithDynamicSpanDrawableBinding

/**
 * https://developer.android.com/reference/android/text/style/DynamicDrawableSpan
 */
class FunWithDynamicSpanDrawableActivity : AppCompatActivity() {

    val binding: ActivityFunWithDynamicSpanDrawableBinding by lazy {
        ActivityFunWithDynamicSpanDrawableBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        checkSpannableInterval()
    }

    private fun checkSpannableInterval() {
        /*
            https://stackoverflow.com/questions/9879233/explain-the-meaning-of-span-flags-like-span-exclusive-exclusive

            Android Spanned, SpannedString, Spannable, SpannableString and CharSequence
            https://stackoverflow.com/questions/17546955/android-spanned-spannedstring-spannable-spannablestring-and-charsequence/41935087#41935087
         */

        testAddingParentheses(binding.testSpannableIntervalInclusiveInclusive, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        testAddingParentheses(binding.testSpannableIntervalInclusiveExclusive, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        testAddingParentheses(binding.testSpannableIntervalExclusiveExclusive, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        testAddingParentheses(binding.testSpannableIntervalExclusiveInclusive, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
    }
}

/*
    TODO -
    Criar um mecanismo que pegue o tamanho da string S crie un intervalo 0 a S e permita
    que o usuario escolha esse intervalo para modificar o tamanho do SpannableStringBuilder
    abaixo
    Range Slider
    https://medium.com/analytics-vidhya/sliders-material-component-for-android-5be61bbe6726
 */

private fun testAddingParentheses(textView: TextView, spanFlag: Int) {
    val start = 1
    val end = 3
    val spannableStringInclusiveInclusive = SpannableStringBuilder(textView.text)
    spannableStringInclusiveInclusive.setSpan(
        ForegroundColorSpan(Color.BLUE),
        start,
        end,
        spanFlag
    )
    /*
        Todo entender porque a ordem de chamada o insert end depois start importa para o resultado
     */
    spannableStringInclusiveInclusive.insert(end, ")")
    spannableStringInclusiveInclusive.insert(start, "(")
    textView.text = spannableStringInclusiveInclusive

}


class CustomDynamicDrawableSpan(
    private val context: Context,
    private val resource: Int
) : DynamicDrawableSpan() {
    override fun getDrawable(): Drawable? {
        val drawable = AppCompatResources.getDrawable(context, resource)
        return drawable?.apply {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        }
    }
}


private fun Context.toSpan(
    content: String,
    resource: Int,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
): SpannableString {
    return SpannableString(content).apply {
        setSpan(CustomDynamicDrawableSpan(this@toSpan, resource), start, end, flag)
    }
}