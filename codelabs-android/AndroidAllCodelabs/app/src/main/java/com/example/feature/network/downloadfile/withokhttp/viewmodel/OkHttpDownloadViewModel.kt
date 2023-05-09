package com.example.feature.network.downloadfile.withokhttp.viewmodel

import androidx.lifecycle.viewModelScope
import com.baseviewmodel.BaseViewModel
import com.baseviewmodel.model.Resource
import com.example.feature.network.downloadfile.utils.downloadFile
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OkHttpDownloadViewModel: BaseViewModel() {

    private val observableChannel = Channel<Resource<String>>(Channel.BUFFERED)

    val observable: Flow<Resource<String>> = observableChannel.receiveAsFlow()

    fun download(url: String): Job =
        viewModelScope.launch {
            downloadFile(url) {

            }
        }

}