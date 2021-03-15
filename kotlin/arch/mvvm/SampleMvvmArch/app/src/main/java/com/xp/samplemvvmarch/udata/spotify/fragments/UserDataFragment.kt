package com.xp.samplemvvmarch.udata.spotify.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xp.samplemvvmarch.R
import com.xp.samplemvvmarch.udata.spotify.viewmodels.UserDataViewModel

class UserDataFragment : Fragment() {

    companion object {
        fun newInstance() =
            UserDataFragment()
    }

    private lateinit var viewModel: UserDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.feature2_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserDataViewModel::class.java)
        // TODO: Use the ViewModel
    }

}