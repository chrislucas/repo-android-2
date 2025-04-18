package com.br.funwithjetpackcompose.tutorials.google.interoperabillity.fragmentsincompose.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.mylibrary.R



/**
 * A simple [Fragment] subclass.
 * Use the [CustomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomFragment : Fragment() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CustomFragment()
    }
}