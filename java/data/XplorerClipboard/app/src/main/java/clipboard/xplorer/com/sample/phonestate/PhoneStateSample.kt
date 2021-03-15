package clipboard.xplorer.com.sample.phonestate

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.telecom.PhoneAccountHandle
import android.telephony.TelephonyManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import clipboard.xplorer.com.sample.clipboard.R
import java.util.*

class PhoneStateSample : AppCompatActivity() {

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQ_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getPhoneNumber()
            }
        }
    }

    private val REQ_PERMISSION = 0x0f

    fun requestPermissions() {
        var permissions: Array<String?>? = null
        permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_NUMBERS)
        } else {
            arrayOf(Manifest.permission.READ_PHONE_STATE)
        }
        val perm: MutableList<String?> = ArrayList()
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission!!) != PackageManager.PERMISSION_GRANTED) {
                perm.add(permission)
            }
        }
        if (perm.size > 0) {
            val n = perm.size
            permissions = arrayOfNulls(n)
            System.arraycopy(perm.toTypedArray(), 0, permissions, 0, n)
            ActivityCompat.requestPermissions(this, permissions, REQ_PERMISSION)
        } else {
            getPhoneNumber()
        }
    }

    private fun getPhoneNumber() {
        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions()
            return
        }
        if (telephonyManager != null) {
            Log.i("TELEPHONY_MANAGER", String.format("SimSerialNumber: %s\nNumber: %s\nDevice Software Version: %s"
                    , telephonyManager.simSerialNumber
                    , telephonyManager.line1Number
                    , telephonyManager.deviceSoftwareVersion
            ))
            var persistableBundle: PersistableBundle? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                persistableBundle = telephonyManager.carrierConfig
                for (key in persistableBundle.keySet()) {
                    val value = persistableBundle[key]
                    if (value != null) {
                        Log.i("TELEPHONY_MANAGER"
                                , String.format("Key: %s, Val: %s", key, value.toString()))
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val componentName = ComponentName(this, ImplConnectionService::class.java)
                val phoneAccountHandle = PhoneAccountHandle(componentName, ImplConnectionService.TAG)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    telephonyManager.createForPhoneAccountHandle(phoneAccountHandle)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_state_sample)
    }
}
