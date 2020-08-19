package com.xp.samplemvvmarch.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.SavedStateViewModelFactory

import com.xp.samplemvvmarch.R
import com.xp.samplemvvmarch.repository.StaticUserRepository
import com.xp.samplemvvmarch.viewmodel.UserProfileViewModelProviderFactory
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



        val factoryUserProfileVM = UserProfileViewModelProviderFactory(StaticUserRepository(1))


        viewModel =
            HelperViewModelProvider.of(this, factoryUserProfileVM, UserProfileViewModel::class.java)

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
