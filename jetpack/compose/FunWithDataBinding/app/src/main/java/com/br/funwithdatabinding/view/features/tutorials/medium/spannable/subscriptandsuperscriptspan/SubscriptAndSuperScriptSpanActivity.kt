package com.br.funwithdatabinding.view.features.tutorials.medium.spannable.subscriptandsuperscriptspan

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.databinding.ActivitySubscriptAndSuperScriptSpanBinding
import java.text.DecimalFormat

/*
    TODO explorar mais esse recurso
    https://www.geeksforgeeks.org/subscript-and-superscript-a-string-in-android-with-kotlin/
 */
class SubscriptAndSuperScriptSpanActivity : AppCompatActivity() {

    private val binding: ActivitySubscriptAndSuperScriptSpanBinding by lazy {
        ActivitySubscriptAndSuperScriptSpanBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.run {
            setContentView(root)
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            val mString = "A2 + B6"
            // Creating a string span
            val mStringSpan = SpannableStringBuilder(mString)

            // Subscripting the string span for "2"
            mStringSpan.setSpan(SubscriptSpan(), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            // Setting the text size ratio for "2"
            // with respect to rest of the span
            mStringSpan.setSpan(RelativeSizeSpan(0.5f), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            // Superscripting the string span for "6"
            mStringSpan.setSpan(SuperscriptSpan(), 6, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            // Setting the text size ratio for "6" with
            // respect to rest of the span
            mStringSpan.setSpan(RelativeSizeSpan(0.5f), 6, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            // Setting the string
            // span to TextView
            simpleText.text = mStringSpan

            val price = DecimalFormat("#.00").format(123.00.toBigDecimal())
            val superScriptSpannableString = SpannableStringBuilder("$\u00A0$price")
            superScriptSpannableString.setSpan(
                SuperscriptSpan(),
                superScriptSpannableString.length - 2,
                superScriptSpannableString.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            superScriptSpannableString.setSpan(
                RelativeSizeSpan(0.5f),
                superScriptSpannableString.length - 2,
                superScriptSpannableString.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            tvPrice.text = superScriptSpannableString

            val pricesWithoutDecimal = SpannableStringBuilder("$\u00A0${price.replace(Regex("\\."), "")}")
            pricesWithoutDecimal.setSpan(
                SuperscriptSpan(),
                pricesWithoutDecimal.length - 2,
                pricesWithoutDecimal.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            pricesWithoutDecimal.setSpan(
                RelativeSizeSpan(0.5f),
                pricesWithoutDecimal.length - 2,
                pricesWithoutDecimal.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tvPriceWithoutDecimal.text = pricesWithoutDecimal
        }
    }
}