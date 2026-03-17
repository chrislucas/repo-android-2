package com.br.funwithdatabinding.view.features.funwithdatabinding

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.funwithdatabinding.view.features.funwithdatabinding.DogImageResponse
import com.br.funwithdatabinding.view.features.funwithdatabinding.DogImageServiceProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogImageViewModel : ViewModel() {
    private var liveData = MutableLiveData<DogImageResponse>()
    val observableLiveData: LiveData<DogImageResponse> = liveData

    private val service = DogImageServiceProvider.provide()

    fun fetchRandomDogImage() {
        service.getDogImage().enqueue(object : Callback<DogImageResponse> {
            override fun onResponse(
                call: Call<DogImageResponse>,
                response: Response<DogImageResponse>
            ) {
                if (response.isSuccessful) {
                    liveData.value = response.body()
                }
            }

            override fun onFailure(p0: Call<DogImageResponse>, throwable: Throwable) {
                Log.e("DOG_IMAGE", "Error On Fetch DogImage $throwable")
            }
        })
    }
}