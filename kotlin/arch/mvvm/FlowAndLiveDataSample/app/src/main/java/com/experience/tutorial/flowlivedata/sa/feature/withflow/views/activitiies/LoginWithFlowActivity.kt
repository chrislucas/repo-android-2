package com.experience.tutorial.flowlivedata.sa.feature.withflow.views.activitiies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.experience.tutorial.databinding.ActivityLoginWithFlowBinding
import com.experience.tutorial.flowlivedata.sa.ext.showSnackBar
import com.experience.tutorial.flowlivedata.sa.feature.withflow.viewmodel.LoginFlowViewModel
import com.experience.tutorial.flowlivedata.sa.feature.utils.viewmodel.MapperViewModelFactory
import com.experience.tutorial.flowlivedata.sa.feature.utils.viewmodel.ProviderViewModel
import com.experience.tutorial.flowlivedata.sa.feature.withlivedata.views.activities.LoginWithLiveDataActivity
import com.experience.tutorial.flowlivedata.sa.models.User
import com.experience.tutorial.flowlivedata.sa.network.model.LoginResponse
import com.experience.tutorial.flowlivedata.sa.utils.Status
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginWithFlowActivity : AppCompatActivity() {

    private val bindView: ActivityLoginWithFlowBinding by lazy {
        ActivityLoginWithFlowBinding.inflate(layoutInflater)
    }

    private val factoryViewModel: MapperViewModelFactory by lazy {
        MapperViewModelFactory()
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

        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                initViewModelObserver()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //setupObservers()
    }

    private fun initViews() {
        bindView.run {
            btnLoginWithFlow.setOnClickListener {
                val email = bindView.etEmailLoginFlowActivity.text.toString()
                val pwd = bindView.etPwdLoginFlowActivity.text.toString()
                // TODO implementar criptografia para enviar esses dados
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
            initViewModelObserver()
            /*
                A class that has an Android lifecycle. These events can be used by custom components
                to handle lifecycle changes without implementing any code inside the Activity or the Fragment


                Wrong usage of repeatOnLifecycle from LoginWithFlowActivity.onResume.

                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    initViewModelObserver()
                }
             */
        }
    }

    private suspend fun initViewModelObserver() {
        viewModel.observerLoginUserFlow.collect { resource ->
            changeViewsPosLogin()
            when (resource.status) {
                Status.Success -> {
                    restartViewsPreLogin()
                    resource.data?.let {
                        processResponse(it)
                    }
                }
                Status.Failure -> {
                    restartViewsPreLogin()
                    resource.data?.let {
                        bindView.root.showSnackBar(it.message)
                    }
                }
                Status.Loading -> {
                    changeViewsPosLogin()
                }
                else -> {
                    restartViewsPreLogin()
                    // DO NOTHING
                    Toast.makeText(this, "STATUS INDEFINIDO", Toast.LENGTH_LONG).show()
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

    private fun restartViewsPreLogin() {
        bindView.btnLoginWithFlow.isEnabled = true
        bindView.loading.visibility = View.GONE
    }

    private fun changeViewsPosLogin() {
        bindView.btnLoginWithFlow.isEnabled = false
        bindView.loading.visibility = View.VISIBLE
    }

}