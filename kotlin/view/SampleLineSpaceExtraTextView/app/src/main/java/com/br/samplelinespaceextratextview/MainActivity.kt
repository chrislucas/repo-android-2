package com.br.samplelinespaceextratextview

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private val ctx: Context = this

    private val spanBottom = "Align Bottom: "
    private val spanBaseline = "Align Baseline: "
    private val spanCenter = "Align Center: "

    private val icon = R.mipmap.ic_launcher_round

    private val stringVerticalAlignmentBottom =
        SpannableStringBuilder(spanBottom).apply {
            //set(7 .. 8, ImageSpan(ctx, R.drawable.sample_drawable_foreground))
            this.setSpan(
                ImageSpan(ctx, icon),
                spanBottom.length - 2,
                spanBottom.length - 1,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
        }

    private val stringVerticalAlignmentBaseline =
        SpannableStringBuilder(spanBaseline).apply {
            this.setSpan(
                ImageSpan(
                    ctx,
                    icon,
                    DynamicDrawableSpan.ALIGN_BASELINE // vertical alignment
                ),
                spanBaseline.length - 2,
                spanBaseline.length - 1,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
        }

    @RequiresApi(Build.VERSION_CODES.Q)
    private val stringVerticalAlignmentCenter =
        SpannableStringBuilder(spanCenter).apply {
            this.setSpan(
                ImageSpan(
                    ctx,
                    icon,
                    DynamicDrawableSpan.ALIGN_CENTER // vertical alignment
                ),
                spanCenter.length - 2,
                spanCenter.length - 1,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.spannable_align_bottom).text = stringVerticalAlignmentBottom

        findViewById<TextView>(R.id.spannable_align_baseline).text =
            stringVerticalAlignmentBaseline

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            findViewById<TextView>(R.id.spannable_align_center).text =
                stringVerticalAlignmentCenter
        }
    }
}