package com.br.funwithhilt.google.studies.view.precomputedtextviewcompat

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.br.funwithhilt.R
import com.br.funwithhilt.databinding.ActivityFunWithPreComputedTextViewCompatBinding

/*
    PrecomputedTextCompat
    https://developer.android.com/reference/androidx/core/text/PrecomputedTextCompat
    -

    What is new in Android P — PrecomputedText
    https://medium.com/appnroll-publication/what-is-new-in-android-p-precomputedtext-2a62ec9e8613
 */
class FunWithPreComputedTextViewCompatActivity : AppCompatActivity() {

    private val binding: ActivityFunWithPreComputedTextViewCompatBinding by lazy {
        ActivityFunWithPreComputedTextViewCompatBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.root.run {
            setContentView(this)
            ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            /*
                add(
                    R.id.fragment_container_view,
                    PrecomputedTextFragment::class.java,
                    null
                )
             */
            add<PrecomputedTextFragment>(R.id.fragment_container_view)
        }
    }
}


