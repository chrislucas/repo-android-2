package com.xp.httprqmod

interface CallbackApi<Success, Error> {
    fun onError(e: Error)
    fun onSuccess(success: Success)
}