package com.br.start.utils

import com.google.gson.Gson
import okhttp3.*
import okio.ByteString
import kotlin.random.Random

class SkipNetworkInterceptor : Interceptor {

    private val MOCK_RESPONSE = listOf(
        "Hello, coroutines!",
        "My favorite feature",
        "Async made easy",
        "Coroutines by example",
        "Check out the Advanced Coroutines codelab next!"
    )

    val gson = Gson()

    private var attemps = 0

    private fun simulateRandomError() = attemps++ % 5 == 0

    private var mockLastResponse = ""

    override fun intercept(chain: Interceptor.Chain): Response {
        simulateNetworkDelay()
        val request = chain.request()
        return if (simulateRandomError()) {
            mockErrorResult(request)
        } else {
            mockSuccessResult(request)
        }

    }

    private fun mockSuccessResult(request: Request): Response {
        var response = ""
        while (response == mockLastResponse) {
            response = MOCK_RESPONSE[Random.nextInt(0, MOCK_RESPONSE.size)]
        }
        mockLastResponse = response


        val body = ResponseBody.create(MediaType.get("application/json"),
            ByteString.encodeUtf8(response))

        return Response.Builder()
            .code(200)
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("OK")
            .body(body).build()
    }

    private fun mockErrorResult(request: Request): Response {
        val json = gson.toJson(
            mapOf("cause" to "not sure")
        )

        val body = ResponseBody.create(MediaType.get("application/json"),
            ByteString.encodeUtf8(json))
        return Response.Builder()
            .code(500)
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("Error")
            .body(body).build()
    }


    private fun simulateNetworkDelay() = Thread.sleep(500L)

}



