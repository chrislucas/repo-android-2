package com.br.restclientlib.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

import retrofit2.Response

object ReactiveServiceCall {


    fun <T> reactiveCallService(call: () -> Single<Response<T>>): Single<Response<T>> =
        call().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}