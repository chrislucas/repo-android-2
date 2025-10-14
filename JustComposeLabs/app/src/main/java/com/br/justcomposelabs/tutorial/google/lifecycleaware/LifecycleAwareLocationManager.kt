package com.br.justcomposelabs.tutorial.google.lifecycleaware

import android.Manifest
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.annotation.RequiresPermission

class LifecycleAwareLocationManager(
    private val context: Context,
    private val listener: (Location) -> Unit
) : DefaultLifecycleObserver, LocationListener {

    private var locationManager: LocationManager? = null

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @RequiresPermission(
        anyOf = [
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ]
    )
    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        // Request location updates when the component starts
        try {
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000, // minTime (ms)
                10f,  // minDistance (m)
                this
            )
        } catch (e: SecurityException) {
            // Handle permission not granted
            e.printStackTrace()
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        // Remove location updates when the component stops
        locationManager?.removeUpdates(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        locationManager = null
    }

    override fun onLocationChanged(location: Location) {
        listener.invoke(location)
    }

    // Implement other LocationListener methods if needed
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}
}