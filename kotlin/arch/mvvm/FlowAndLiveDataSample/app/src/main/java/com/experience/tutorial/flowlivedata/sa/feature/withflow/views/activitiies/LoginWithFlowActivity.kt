package com.experience.tutorial.flowlivedata.sa.feature.withflow.views.activitiies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.experience.tutorial.databinding.ActivityLoginWithFlowBinding
import com.experience.tutorial.flowlivedata.sa.ext.showSnackBar
import com.experience.tutorial.flowlivedata.sa.feature.withflow.repositories.LoginFlowRepository
import com.experience.tutorial.flowlivedata.sa.feature.withflow.viewmodel.LoginFlowViewModel
import com.experience.tutorial.flowlivedata.sa.feature.withlivedata.viewmodel.MapperViewModelFactory
import com.experience.tutorial.flowlivedata.sa.feature.withlivedata.viewmodel.ProviderViewModel
import com.experience.tutorial.flowlivedata.sa.feature.withlivedata.views.activities.LoginWithLiveDataActivity
import com.experience.tutorial.flowlivedata.sa.models.User
import com.experience.tutorial.flowlivedata.sa.network.LoginEndpoint
import com.experience.tutorial.flowlivedata.sa.network.ProviderEndpointClient
import com.experience.tutorial.flowlivedata.sa.network.model.LoginResponse
import com.experience.tutorial.flowlivedata.sa.utils.Status
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginWithFlowActivity : AppCompatActivity() {

    private val bindView: ActivityLoginWithFlowBinding by lazy {
        ActivityLoginWithFlowBinding.inflate(layoutInflater)
    }

    private val loginEndpoint: LoginEndpoint = ProviderEndpointClient.mockLoginEndpointSuccess()

    private val factoryViewModel: MapperViewModelFactory by lazy {
        MapperViewModelFactory(LoginFlowRepository::class.java, loginEndpoint)
    }

    private val viewModel: LoginFlowViewModel by lazy {
        ProviderViewModel.provide(
            viewModelStore,
            factoryViewModel,
            LoginFlowViewModel::class.java
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
        bindView.run {
            btnLoginWithFlow.setOnClickListener {
                val email = bindView.etEmailLoginFlowActivity.text.toString()
                val pwd = bindView.etPwdLoginFlowActivity.text.toString()
                val user = User(email, pwd)
                viewModel.login(user)
            }

            tvUseAdviceFlow.setOnClickListener {
                val intent = Intent(this@LoginWithFlowActivity, LoginWithLiveDataActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    private fun toggleLoginButton() {
        bindView.btnLoginWithFlow.let {
            it.isEnabled = !it.isEnabled
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

    private fun setupObservers() {
        /**
         * https://developer.android.com/topic/libraries/architecture/coroutines?hl=pt-br
         */
        lifecycleScope.launch {
            viewModel.observerLoginUserFlow.collect { resource ->
                toggleViews()
                when (resource.status) {
                    Status.Success -> {
                        resource.data?.let {
                            processResponse(it)
                        }
                    }
                    Status.Failure -> {
                        resource.data?.let {
                            bindView.root.showSnackBar(it.message)
                        }
                    }
                    else -> {
                        // DO NOTHING
                    }
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

    private fun toggleViews() {
        toggleProgressBar()
        toggleLoginButton()
    }
}