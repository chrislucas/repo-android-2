package com.br.funwithjetpackcompose.tutorials.google.interoperabillity.usingcomposeinviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.mylibrary.R
import com.br.mylibrary.databinding.FragmentComposableViewBinding
import kotlin.lazy

/*
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/compose-in-views?authuser=1#compose-in-fragments
 */
class ComposableViewFragment : Fragment() {

    private var _binding: FragmentComposableViewBinding? = null

    /*
        Como usar o view binding no fragment
        https://developer.android.com/topic/libraries/view-binding#fragments
        TODO testar a ideia de usar o by lazy
     */
    private val binding: FragmentComposableViewBinding? get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentComposableViewBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ComposableViewFragment().apply {
            }
    }
}