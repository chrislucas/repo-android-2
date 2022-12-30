package com.br.funrestapi.feature.funmarvelapi.characters.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.funrestapi.R



/**
 * A simple [Fragment] subclass.
 * Use the [AllMarvelCharacterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllMarvelCharacterFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_marvel_character, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllMarvelCharacterFragment()
    }
}