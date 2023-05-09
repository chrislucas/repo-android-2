package com.example.feature.network.downloadfile.withokhttp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.baseviewmodel.ViewModelBuilder
import com.baseviewmodel.ViewModelFactory
import com.baseviewmodel.model.Status
import com.example.androidallcodelabs.databinding.ActivityOkHttpDownloadFileBinding
import com.example.feature.network.downloadfile.withokhttp.viewmodel.OkHttpDownloadViewModel
import kotlinx.coroutines.launch

/**
 * https://medium.com/mobile-app-development-publication/download-file-in-android-with-kotlin-874d50bccaa2
 * https://square.github.io/okhttp/#post-to-a-server
 */


class OkHttpDownloadFileActivity : AppCompatActivity() {

    private val bind: ActivityOkHttpDownloadFileBinding by lazy {
        ActivityOkHttpDownloadFileBinding.inflate(layoutInflater)
    }

    private val factoryViewModel: ViewModelFactory by lazy { ViewModelFactory() }

    private val viewModel: OkHttpDownloadViewModel by lazy {
        ViewModelBuilder.build(viewModelStore, factoryViewModel, OkHttpDownloadViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        initViewListener()

        /*
            Deprecated
            https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleCoroutineScope
         */
        lifecycleScope.launchWhenStarted {}

        /**
         * TODO ler e resumir para entender o pq launchWhenStarted tornou-se obsoleto
         * https://medium.com/androiddevelopers/repeatonlifecycle-api-design-story-8670d1a7d333
         */

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                initViewModelObserver()
            }
        }
    }

    private suspend fun initViewModelObserver() {
        viewModel.observable.collect { resource ->
            when(resource.status) {
                Status.Success -> {
                    Log.i("SUCESSO", "")
                }

                Status.Waiting -> {

                }

                else -> {

                }
            }
        }
    }

    private fun initViewListener() {
        bind.btStartDownloadOkHttp.setOnClickListener {
            viewModel.download("")
        }
    }
}