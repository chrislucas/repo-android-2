package com.br.buildlocationawareapps.utils

import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.jvm.Throws

/*
    https://blog.stackademic.com/callbackflow-callback-to-flow-api-in-kotlin-2229e7332ed2

    Set up Google Play services
    https://developers.google.com/android/guides/setup?device=phone-tablet
    https://developers.google.com/android/reference/com/google/android/gms/location/package-summary

 */


@androidx.annotation.RequiresPermission(
    allOf = [
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ]
)
fun Context.locationUpdatesFlow(
    onFailure: (Throwable?) -> Unit = {},
    onLocationAvailability: (LocationAvailability) -> Unit = { }
): Flow<Location> = callbackFlow {
    val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(this@locationUpdatesFlow)

    val locationCallback = object : LocationCallback() {
        override fun onLocationAvailability(locationAvailability: LocationAvailability) {
            super.onLocationAvailability(locationAvailability)
            onLocationAvailability(locationAvailability)
        }

        override fun onLocationResult(result: LocationResult) {
            super.onLocationResult(result)
            result.locations.forEach { location ->
                trySendBlocking(location).onFailure { throwable ->
                    onFailure(throwable)
                }
            }
        }
    }

    /*
        https://tomas-repcik.medium.com/locationrequest-create-got-deprecated-how-to-fix-it-e4f814138764
     */
    val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,
        10000
    ).apply {
        setMinUpdateDistanceMeters(100f)
        setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
        setWaitForAccurateLocation(true)
    }.build()

    fusedLocationClient.requestLocationUpdates(
        locationRequest,
        locationCallback,
        Looper.getMainLooper()
    )

    awaitClose {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}