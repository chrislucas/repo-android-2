package com.br.network.utils.compat.rxjava2

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

fun <T> reactiveCallService(call: () -> Single<Response<T>>): Single<Response<T>> =
    call().subscribeOn(Schedulers.io()).observeOn(Schedulers.single())