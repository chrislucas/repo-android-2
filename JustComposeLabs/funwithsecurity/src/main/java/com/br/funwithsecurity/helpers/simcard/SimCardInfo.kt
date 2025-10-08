package com.br.funwithsecurity.helpers.simcard

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import androidx.annotation.RequiresPermission

/*
    Pesquisar por: SIM Card android api
 */


fun Context.getSimOperatorName(): String? {
    return (getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager)?.simOperatorName
}

@RequiresPermission(allOf = [Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.READ_PHONE_STATE])
data class SimCardInfo(private val context: Context) {
    val name: String? =
        (context.getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager)?.simOperatorName
    val code: String? =
        (context.getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager)?.simCountryIso

    @SuppressLint("MissingPermission", "HardwareIds")
    val serial: String? =
        (context.getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager)?.simSerialNumber


}


@RequiresPermission("android.permission.READ_PRIVILEGED_PHONE_STATE")
fun Context.simSerialNumber(): String? =
    (getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager)?.simSerialNumber


@RequiresPermission(allOf = [Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.READ_PHONE_STATE])
fun Context.simPhoneNumer() =  (getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager)?.line1Number


object SubscriptionInfoHelper {

    /*
        SubscriptionManager android example
     */

    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    fun showSubscriptionInfo(context: Context) {
        val subscriptionManager = context
            .getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager?

        subscriptionManager?.activeSubscriptionInfoList?.forEach { subscriptionInfo ->

        }
    }
}