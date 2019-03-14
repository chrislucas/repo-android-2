package br.com.xplorer.simplebroadcastreceiver

import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // exemplo besta para explicar extension method para um amigo
    private fun Int.isOdd() : Boolean = (this and 1) == 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = IntentFilter()
        intent.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        intent.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        intent.addAction(WifiManager.ACTION_PICK_WIFI_NETWORK)
        val p = 2
        Toast.makeText(this
            , "$p eh ${if (2.isOdd()) "Impar" else "Par"}"
            , Toast.LENGTH_LONG).show()

        //registerReceiver(WifiReceiver(), intent)
    }
}
