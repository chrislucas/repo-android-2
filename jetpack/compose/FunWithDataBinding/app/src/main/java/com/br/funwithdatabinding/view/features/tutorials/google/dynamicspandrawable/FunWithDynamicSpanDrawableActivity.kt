package com.br.funwithdatabinding.view.features.tutorials.google.dynamicspandrawable

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.DynamicDrawableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
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
-
 */
class FunWithDynamicSpanDrawableActivity : AppCompatActivity() {

    private val binding: ActivityFunWithDynamicSpanDrawableBinding by lazy {
        ActivityFunWithDynamicSpanDrawableBinding.inflate(layoutInflater)
    }


    private val icFlake = R.drawable.ic_flake


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        checkSpannableInterval()
        testDynamicDrawableSpanAlignCenter()
        testCenteredImageSpan()
        testImageSpan()
    }

    private fun testDynamicDrawableSpanAlignBaseline() {
        val text = binding.testDynamicDrawableSpan.text
        val startIdx = text.indexOf("[ ]")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.testDynamicDrawableSpan.text =
                toSpannableString(
                    text,
                    span = CustomDynamicDrawableSpan(this, icFlake),
                    startIdx + 1,
                    startIdx + 2,
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE
                )
        }
    }


    private fun testDynamicDrawableSpanAlignCenter() {
        val text = binding.testDynamicDrawableSpan.text
        val startIdx = text.indexOf("[ ]")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.testDynamicDrawableSpan.text =
                toSpannableString(
                    text,
                    CustomDynamicDrawableSpan(
                        this,
                        icFlake,
                        DynamicDrawableSpan.ALIGN_CENTER
                    ),
                    startIdx + 1,
                    startIdx + 2,
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE
                )
        }
    }


    private fun testDynamicDrawableSpanAlignBottom() {
        val text = binding.testDynamicDrawableSpan.text
        val startIdx = text.indexOf("[ ]")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.testDynamicDrawableSpan.text =
                toSpannableString(
                    text,
                    span = CustomDynamicDrawableSpan(
                        this,
                        icFlake,
                        DynamicDrawableSpan.ALIGN_BOTTOM
                    ),
                    startIdx + 1,
                    startIdx + 2,
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE
                )
        }
    }

    private fun testAnotherDynamicDrawableSpan() {
        val text = binding.testDynamicDrawableSpan.text
        val startIdx = text.indexOf("[ ]")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.testDynamicDrawableSpan.text =
                toSpannableString(
                    text,
                    icFlake,
                    startIdx + 1,
                    startIdx + 2,
                    alignmentType = DynamicDrawableSpan.ALIGN_CENTER
                )
        }
    }

    private fun testCenteredImageSpan() {
        AppCompatResources.getDrawable(this, icFlake)?.let { drawable ->
            val text = binding.testCenteredImageSpan.text
            val startIdx = text.indexOf("[ ]")
            binding.testCenteredImageSpan.text = toSpannableString(
                text,
                CenteredImageSpan(drawable),
                startIdx + 1,
                startIdx + 2
            )
        }
    }


    /*
        https://developer.android.com/reference/android/text/style/ImageSpan
     */
    private fun testImageSpan() {
        AppCompatResources.getDrawable(this, icFlake)?.let { drawable ->
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            val text = binding.testImageSpan.text
            val startIdx = text.indexOf("[ ]")
            binding.testImageSpan.text = toSpannableString(
                text,
                ImageSpan(drawable),
                startIdx + 1,
                startIdx + 2
            )
        }
    }

    private fun checkSpannableInterval() {
        /*
            https://stackoverflow.com/questions/9879233/explain-the-meaning-of-span-flags-like-span-exclusive-exclusive

            Android Spanned, SpannedString, Spannable, SpannableString and CharSequence
            https://stackoverflow.com/questions/17546955/android-spanned-spannedstring-spannable-spannablestring-and-charsequence/41935087#41935087
         */

        testAddingParentheses(
            binding.testSpannableIntervalInclusiveInclusive,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        testAddingParentheses(
            binding.testSpannableIntervalInclusiveExclusive,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        testAddingParentheses(
            binding.testSpannableIntervalExclusiveExclusive,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        testAddingParentheses(
            binding.testSpannableIntervalExclusiveInclusive,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
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
}

