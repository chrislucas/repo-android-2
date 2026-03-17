package com.br.coroutinecodelabs.start.utils

import com.google.gson.Gson
import okhttp3.Interceptor

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody



private val FAKE_RESULTS = listOf(
    "Hello, coroutines!",
    "My favorite feature",
    "Async made easy",
    "Coroutines by example",
    "Check out the Advanced Coroutines codelab next!"
)

class SkipNetworkInterceptor : Interceptor {

    private var lastResult = ""

    val gson = Gson()

    private var attempts = 0

    private fun shouldThrowMockedError() = attempts++ % 5 == 0

    override fun intercept(chain: Interceptor.Chain): Response {
        mockDelay()
        return if (shouldThrowMockedError()) {
            mockErrorRequest(chain.request())
        } else {
            mockSuccessfulRequest(chain.request())
        }
    }

    private fun mockDelay() = Thread.sleep(500)

    private fun mockErrorRequest(request: Request) = Response.Builder()
        .code(500)
        .request(request)
        .message("Bad Request")
        .body(
            ResponseBody.create(
                "application/json".toMediaType(),
                gson.toJson(mapOf("error" to "Bad Request"))
            )
        )
        .build()

    private fun mockSuccessfulRequest(request: Request): Response {
        var nexResult = lastResult
        while (nexResult == lastResult) {
            nexResult = FAKE_RESULTS.random()
        }
        lastResult = nexResult

        return Response.Builder()
            .code(200)
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("OK")
            .body(
                ResponseBody.create(
                    "application/json".toMediaType(),
                    gson.toJson(mapOf("result" to nexResult))
                )
            )
            .build()
    }

}