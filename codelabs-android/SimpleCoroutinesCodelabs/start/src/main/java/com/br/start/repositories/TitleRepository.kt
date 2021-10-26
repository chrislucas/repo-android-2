package com.br.start.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.br.start.network.MainEndpoint
import com.br.start.persistency.Title
import com.br.start.persistency.TitleDao
import com.br.start.utils.executorService

class TitleRepository(private val endpoint: MainEndpoint, private val titleDao: TitleDao) {

    val title: LiveData<String?> = titleDao.titleLiveData.map { it?.title }

    /**
     * Atualiza o titulo e salva-o numa tabela de cache offline
     * */

    fun refreshTitle(titleRefreshCallback: TitleRefreshCallback) {
        executorService(2).submit {
            try {

                val result = endpoint.fetchNextTitle().execute()
                if (result.isSuccessful) {
                    result.body()?.let {
                        titleDao.insert(Title(it))
                    }
                    titleRefreshCallback.onCompleted()
                } else {
                    titleRefreshCallback
                        .onError(
                            TitleRefreshErrorMessage(
                                "Não foi possivel atualizar o título",
                                null
                            )
                        )
                }

            } catch (cause: Throwable) {
                titleRefreshCallback.onError(
                    TitleRefreshErrorMessage(
                        "Não foi possivel atualizar o título",
                        cause
                    )
                )
            }
        }
    }
}


class TitleRefreshErrorMessage(override val message: String, override val cause: Throwable?) :
    Throwable(message, cause)

interface TitleRefreshCallback {
    fun onCompleted()
    fun onError(cause: Throwable)
}