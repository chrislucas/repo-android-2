package com.br.funwithdatabinding.view.features.funwithdatabinding


import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://dog.ceo/api/breeds/image/"
private const val END_POINT = "random"

data class DogImageResponse(
    @SerializedName("message") val imageUrl: String,
    val status: String
)


interface DogImageService {
    @GET(END_POINT)
    fun getDogImage(): Call<DogImageResponse>
}


object DogImageServiceProvider {

    private lateinit var retrofit: Retrofit

    private fun createInstance(): Retrofit {
        if(!this::retrofit.isInitialized) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    fun provide(): DogImageService = createInstance().create(DogImageService::class.java)
}