package com.br.justcomposelabs.tutorial.google.lifecycle.sample.locationmanager

import android.Manifest
import android.location.Location
import android.location.LocationManager
import androidx.annotation.RequiresPermission

/*
    https://medium.com/pickme-engineering-blog/building-a-modern-android-location-manager-from-legacy-approaches-to-clean-architecture-excellence-3e3e4590533e
 */
class GetLastLocationUseCase(
    private val locationManager: LocationManager
) {

    @RequiresPermission(
        allOf = [Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION]
    )
    suspend operator fun invoke(provider: String): Result<Location?> {
        return Result.success(
            locationManager.getLastKnownLocation(provider)
        )
    }
}