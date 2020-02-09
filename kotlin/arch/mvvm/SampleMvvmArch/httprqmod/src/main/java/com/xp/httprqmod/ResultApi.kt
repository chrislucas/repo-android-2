package com.xp.httprqmod

open class ResultApi<Success, Error> : CallbackApi<Success, Error> {

    override fun onError(e: Error) {}

    override fun onSuccess(success: Success) {}

}