package com.br.funwithdatabinding.view.features.utils


import androidx.fragment.app.FragmentActivity
import com.br.funwithdatabinding.BuildConfig
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.tasks.await
import timber.log.Timber


/**

https://firebase.google.com/docs/remote-config/?hl=pt&authuser=1&_gl=1*w8m7ff*_ga*MTY1MTM4NDg5OS4xNzI1MjM0Nzcz*_ga_CW55HF8NVT*MTcyNTIzODE3Ny4yLjEuMTcyNTI0MTM1OC4zOC4wLjA.#how_does_it_work

How does it work?
- RemoteConfig inclui uma biblioteca de cliente que lida com taredas como
buscar valores de parametros e armazenalos em cache/

- A biblioteca prove metodos de acesso através de um ponto unico para recuperar valores. O app
pode recuperar valores de Remote Configu usando a mesma logica usada no app 'in-app default values'

- Podemos sobreescrever os valores default do in-app usando Firebase consolr ou a api backend
do Remote Config criando parametros com o mesmo nome.

- Para cada parametro podemos definir um valor padrao no Remote Config e sobreescrever o
valor definido no in-app
- Podemos criar valores condicionais para sobreescrever os valores padroes no in-app


Understand real-time Remote Config
https://firebase.google.com/docs/remote-config/real-time?authuser=1&platform=ios
 */

class FirebaseRemoteConfigProxy(
    configUpdateListener: ConfigUpdateListener = ConfigUpdateListenerDefault()
) {
    private val remoteConfig = Firebase.remoteConfig

    var spTransToken: String? = null

    init {
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.addOnConfigUpdateListener(configUpdateListener)
        /*
            TODO adicionar num DataStore criptografado para nao ter que chamar o remote/
         */
        spTransToken = remoteConfig.getString(SP_TRANSPORT_TOKEN_KEY)
    }

    companion object Companion {
        private val configSettings = remoteConfigSettings { minimumFetchIntervalInSeconds = 120 }
        const val SP_TRANSPORT_TOKEN_KEY = "sptranstoken"
    }


    /*
        https://firebase.google.com/docs/remote-config/get-started?authuser=1&platform=android#fetch-values
     */
    fun fetchAndActivate(activity: FragmentActivity, callback: (Task<Boolean>) -> Unit) =
        remoteConfig.fetchAndActivate().addOnCompleteListener(activity, callback)


    fun fetchToken(callback: (Task<Boolean>) -> Unit) {
        remoteConfig.fetchAndActivate().addOnCompleteListener(callback)
    }

    suspend fun fetchToken(): String  {
        remoteConfig.fetchAndActivate().await()
        return remoteConfig.getString(SP_TRANSPORT_TOKEN_KEY)
    }
}


class ConfigUpdateListenerDefault : ConfigUpdateListener {
    /**
     * https://firebase.google.com/docs/remote-config/get-started?authuser=1&platform=android
     */
    override fun onUpdate(configUpdate: ConfigUpdate) {
    }

    override fun onError(error: FirebaseRemoteConfigException) {
        if(BuildConfig.DEBUG) {
            Timber.tag("FB_REMOTE_CONFIG").e("$error")
        }
    }
}