package com.br.samplepackagevisibilityandroid11

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi

val pairsPackageFlagAndMinVersionCode = arrayOf(
    PackageManager.GET_META_DATA to null,
    PackageManager.GET_SHARED_LIBRARY_FILES to null,
    PackageManager.MATCH_ALL to Build.VERSION_CODES.M,
    PackageManager.MATCH_DEFAULT_ONLY to null,
    PackageManager.MATCH_DISABLED_COMPONENTS to Build.VERSION_CODES.N,
    PackageManager.MATCH_DISABLED_UNTIL_USED_COMPONENTS to Build.VERSION_CODES.M,
    PackageManager.MATCH_DIRECT_BOOT_AUTO to Build.VERSION_CODES.O,
    PackageManager.MATCH_DIRECT_BOOT_AWARE to Build.VERSION_CODES.N,
    PackageManager.MATCH_DIRECT_BOOT_UNAWARE to Build.VERSION_CODES.N,
    PackageManager.MATCH_SYSTEM_ONLY to Build.VERSION_CODES.N,
    PackageManager.MATCH_UNINSTALLED_PACKAGES to Build.VERSION_CODES.N,
)

val resultOrOperationFlags = pairsPackageFlagAndMinVersionCode.map { it.first }
    .reduce { acc, next -> acc or next }


@RequiresApi(Build.VERSION_CODES.Q)
fun PackageManager.getLogInstalledModules() =
    Pair(PackageManager.MATCH_ALL, getInstalledModules(PackageManager.MATCH_ALL))


fun PackageManager.getLogInstalledPackages(): Pair<Int, List<PackageInfo>> {
    val resultOrOperationFlags = pairsPackageFlagAndMinVersionCode
        .map { it.first }
        .reduce { acc, next -> acc or next }

    return Pair(resultOrOperationFlags, getInstalledPackages(resultOrOperationFlags))

}


fun PackageManager.getLogApplicationInstalled(): Map<Int, List<PackageInfo>> {
    return pairsPackageFlagAndMinVersionCode.associate { (flag, minSdk) ->
        val list = minSdk?.let { sdk ->
            if (Build.VERSION.SDK_INT >= sdk) {
                getInstalledPackages(flag)
            } else {
                listOf()
            }
        } ?: getInstalledPackages(flag)
        flag to list
    }
}