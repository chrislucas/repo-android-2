package com.xp.samplemvvmarch.feature2

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xp.samplemvvmarch.R

class Feature2Fragment : Fragment() {

    companion object {
        fun newInstance() = Feature2Fragment()
    }

    private lateinit var viewModel: Feature2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.feature2_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Feature2ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}