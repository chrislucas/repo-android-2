package com.br.funwithdatabinding.view.features.tutorials.google.sec.keystore

import android.security.keystore.KeyProperties
import java.security.KeyPair
import java.security.KeyPairGenerator

/*
    TODO
    https://developer.android.com/privacy-and-security/keystore?hl=pt-br
    KeyStore
    https://developer.android.com/reference/java/security/KeyStore
    KeyInfo
    https://developer.android.com/reference/android/security/keystore/KeyInfo

    Protegendo seu aplicativo Android com o Android KeyStore
    https://medium.com/@kmichel.jae/protegendo-seu-aplicativo-android-com-o-android-keystore-2fc11e453035

    https://medium.com/@jerry.cho.dev/android-keystore-aa7d2b43adfe
 */



fun kpgEllipticCurve(content: String): KeyPair {
    /*
        https://developer.android.com/privacy-and-security/keystore?hl=pt-br#GeneratingANewPrivateKey
     */
    val kpgEllipticCurve = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_EC, content)


    return kpgEllipticCurve.generateKeyPair()
}