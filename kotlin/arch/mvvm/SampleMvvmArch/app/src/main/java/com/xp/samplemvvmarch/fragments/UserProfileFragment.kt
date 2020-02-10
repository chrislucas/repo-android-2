package com.xp.samplemvvmarch.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.xp.samplemvvmarch.R
import com.xp.samplemvvmarch.repository.LocalUserRepository
import com.xp.samplemvvmarch.viewmodel.FactoryUserProfileViewModel
import com.xp.samplemvvmarch.viewmodel.FactoryViewModel
import com.xp.samplemvvmarch.viewmodel.HelperViewModelProvider
import com.xp.samplemvvmarch.viewmodel.UserProfileViewModel

class UserProfileFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(): UserProfileFragment =
            UserProfileFragment()
    }

    private lateinit var savedStateViewModelFactory: SavedStateViewModelFactory
    private lateinit var viewModel: UserProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedStateViewModelFactory =
            SavedStateViewModelFactory(activity?.application!!, this)

        val factory1 = FactoryViewModel(
            arrayOf(Int::class.java),
            arrayOf(1)
        )

        val factory2 = FactoryUserProfileViewModel(LocalUserRepository(1))


        viewModel =
            HelperViewModelProvider.of(this, factory2, UserProfileViewModel::class.java)

        return inflater.inflate(R.layout.user_profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // teste

        viewModel.run {
            val fn = { lifecycle }

            userLiveData.observe(fn) { user ->
                view.findViewById<TextView>(R.id.user_id).text = "${user.id}"
                view.findViewById<TextView>(R.id.user_name).text = user.name
            }
        }
    }

    fun getTagFragment(): String = javaClass.name


    override fun onResume() {
        super.onResume()
        viewModel.get()
    }

}
