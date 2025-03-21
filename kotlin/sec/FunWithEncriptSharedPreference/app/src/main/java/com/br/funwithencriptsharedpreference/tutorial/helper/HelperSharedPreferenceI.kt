package com.br.funwithencriptsharedpreference.tutorial.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKey.DEFAULT_MASTER_KEY_ALIAS
import androidx.security.crypto.MasterKey.KeyScheme
import androidx.security.crypto.MasterKey.KeyScheme.AES256_GCM
import kotlin.reflect.KProperty

/**
 * https://proandroiddev.com/encrypted-preferences-in-android-af57a89af7c8
 */

fun Context.providerMasterKeyAlias(keyAlias: String = DEFAULT_MASTER_KEY_ALIAS, keyScheme: KeyScheme = AES256_GCM) =
    MasterKey
        .Builder(this, keyAlias)
        .setKeyScheme(keyScheme)
        .build()

/*
    https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences
    - MasterKey : https://developer.android.com/reference/androidx/security/crypto/MasterKey
        -
    - MasterKey.Builder & EncryptedSharedPreferences: https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences
 */



fun Context.providerEncryptedSharedPreferenceDefault(filename: String): SharedPreferences {

    /*
        https://proandroiddev.com/encrypted-preferences-in-android-af57a89af7c8
        Criar a masterke
     */

    val providerDefaultMasterKeyAlias = MasterKey
        .Builder(this)
        .setKeyScheme(AES256_GCM)
        .build()

    /*
        https://proandroiddev.com/encrypted-preferences-in-android-af57a89af7c8

        Inicializar o EncryptedSharedPreference
     */

    return EncryptedSharedPreferences.create(
        this,
        filename,
        providerDefaultMasterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )
}

fun createEncryptedSharedPreferences(filename: String, ctx: Context): SharedPreferences {
    /*
        https://proandroiddev.com/encrypted-preferences-in-android-af57a89af7c8
        Criar a masterke
     */

    val providerDefaultMasterKeyAlias = MasterKey
        .Builder(ctx)
        .setKeyScheme(KeyScheme.AES256_GCM)
        .build()

    // Deprecated
    // MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    /*
        https://proandroiddev.com/encrypted-preferences-in-android-af57a89af7c8

        Inicializar o EncryptedSharedPreference
     */

    return EncryptedSharedPreferences.create(
        ctx,
        filename,
        providerDefaultMasterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )
}

fun providerEncryptedSharedPreference(
    filename: String,
    ctx: Context,
    transform: (filename: String, ctx: Context) -> SharedPreferences
): SharedPreferences = transform(filename, ctx)

class ProviderEncryptedSharedPreferences(
    private val filename: String,
    private val create: (filename: String) -> SharedPreferences
) {
    operator fun getValue(ref: Any?, property: KProperty<*>): SharedPreferences =
        create(filename)
}

private val Context.encryptedSharedPreferences: ProviderEncryptedSharedPreferences
    get() {
        return ProviderEncryptedSharedPreferences("") { filename ->
            val providerDefaultMasterKeyAlias = MasterKey
                .Builder(this)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

            EncryptedSharedPreferences.create(
                this,
                filename,
                providerDefaultMasterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
            )
        }
    }




