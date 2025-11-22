package com.br.funwithsecurity.helpers.simcard

import android.content.Context
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager

/*
    Pesquisar por: SIM Card android api
 */


fun Context.getSimOperatorName(): String? {
    return (getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager)?.simOperatorName
}


data class SimCardInfo(private val context: Context) {
    val name: String? =
        (context.getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager)?.simOperatorName
    val code: String? =
        (context.getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager)?.simCountryIso

    /*
    val serial: String? =
        (context.getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager)?.simSerialNumber

     */
}

/*
fun Context.simSerialNumber(): String? =
    (getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager)?.simSerialNumber
    
 */

/*
fun Context.simPhoneNumer() =
    (getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager)?.line1Number

 */


object SubscriptionInfoHelper {

    /*
        SubscriptionManager android example
     */

    fun showSubscriptionInfo(context: Context) {
        val subscriptionManager = context
            .getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager?
/*
        subscriptionManager?.activeSubscriptionInfoList?.forEach { subscriptionInfo ->

        }

 */
    }
}