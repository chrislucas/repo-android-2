package com.experience.tutorial.flowlivedata.sa.feature.withlivedata.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.experience.tutorial.R
import com.experience.tutorial.databinding.ActivityLoginWithLivedataBinding
import com.experience.tutorial.flowlivedata.sa.ext.showSnackBar
import com.experience.tutorial.flowlivedata.sa.feature.withflow.views.activitiies.LoginWithFlowActivity
import com.experience.tutorial.flowlivedata.sa.feature.withlivedata.repositories.LoginRepository
import com.experience.tutorial.flowlivedata.sa.feature.withlivedata.viewmodel.LoginWithLivedataViewModel
import com.experience.tutorial.flowlivedata.sa.feature.withlivedata.viewmodel.MapperViewModelFactory
import com.experience.tutorial.flowlivedata.sa.feature.withlivedata.viewmodel.ProviderViewModel
import com.experience.tutorial.flowlivedata.sa.models.User
import com.experience.tutorial.flowlivedata.sa.network.LoginEndpoint
import com.experience.tutorial.flowlivedata.sa.network.LoginEndpointHelper
import com.experience.tutorial.flowlivedata.sa.network.ProviderEndpointClient
import com.experience.tutorial.flowlivedata.sa.network.model.LoginResponse
import com.experience.tutorial.flowlivedata.sa.utils.Status

/**
 * From this
 * https://proandroiddev.com/flow-livedata-what-are-they-best-use-case-lets-build-a-login-system-39315510666d
 *
 * */
class LoginWithLiveDataActivity : AppCompatActivity() {

    private val bindView: ActivityLoginWithLivedataBinding by lazy { ActivityLoginWithLivedataBinding.inflate(layoutInflater) }

    private val endpoint: LoginEndpoint by lazy { ProviderEndpointClient.provide() }

    // private val repository: LoginRepository by lazy { LoginRepository(LoginEndpointHelper(endpoint)) }

    private val factoryViewModel: MapperViewModelFactory by lazy {
        MapperViewModelFactory(LoginRepository::class.java, LoginEndpointHelper(endpoint))
    }

    private val viewModel: LoginWithLivedataViewModel by lazy {
        ProviderViewModel.provide(
            viewModelStore,
            factoryViewModel,
            LoginWithLivedataViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindView.root)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        setupObservers()
    }

    private fun initViews() {
        addClickListener()
    }

    private fun addClickListener() {
        bindView.btnLogin.setOnClickListener {
            onClickButtonLogin()
        }

        bindView.txtUseAdviceLivedata.setOnClickListener {
            startActivity(Intent(this, LoginWithFlowActivity::class.java))
        }
    }

    private fun toggleProgressBar() {
        bindView.loading.let {
            it.visibility = if (it.visibility == View.VISIBLE) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
    }

    private fun toggleLoginButton() {
        bindView.btnLogin.let {
            it.isEnabled = !it.isEnabled
        }
    }

    private fun toggleLogin() {
        toggleProgressBar()
        toggleLoginButton()
    }

    private fun setupObservers() {
        viewModel.observerLoginData.observe(this) { resource ->
            when (resource.status) {
                Status.Success -> {
                    toggleLogin()
                    resource.data?.let { response ->
                        processResponse(response)
                    }
                }

                Status.Error -> {
                    toggleLogin()
                }

                Status.Loading -> {
                    toggleLogin()
                }

                Status.Failure -> {
                    toggleLogin()
                }
            }
        }
    }

    private fun processResponse(response: LoginResponse) {
        when (response.status) {
            "fail" -> {
                bindView.root.showSnackBar(response.message)
            }

            "success" -> {
                Toast.makeText(this, "Login com sucesso", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onClickButtonLogin() {
        val email = bindView.etEmailLoginLdActivity.text.toString()
        val pwd = bindView.etPwdLoginLdActivity.text.toString()
        val user = User(email, pwd)
        viewModel.login(user)
    }
}

