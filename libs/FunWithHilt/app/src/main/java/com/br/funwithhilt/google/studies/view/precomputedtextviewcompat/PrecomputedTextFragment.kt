package com.br.funwithhilt.google.studies.view.precomputedtextviewcompat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.funwithhilt.R

/*
    PrecomputedTextCompat
    https://developer.android.com/reference/androidx/core/text/PrecomputedTextCompat

    Ciclo de vida KTX
    https://developer.android.com/kotlin/ktx#lifecycle
 */
class PrecomputedTextFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_precomputed_text,
            container,
            false
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PrecomputedTextFragment()
    }
}