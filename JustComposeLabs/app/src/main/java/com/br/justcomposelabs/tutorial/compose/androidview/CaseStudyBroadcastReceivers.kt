package com.br.justcomposelabs.tutorial.compose.androidview

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import timber.log.Timber

/*
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/views-in-compose
    - Exemplo de um BroadcastReceiver que precisa ser registrado atraves de uma composable
 */


@Composable
fun SystemBroadcastReceiver(
    systemActions: List<String>,
    onSystemEvent: (intent: Intent?) -> Unit
) {

    val context = LocalContext.current
    /*
        Recuperar a última lambda function onSystemEvent passada para a funcao composable

        rememberUpdatedState: reference a value in an effect that
        shouldn't restart if the value changes
            - Referenciar um valor passado para composable que nao reinicia quando
            esse parametro muda e ocorre a recomposicao
        https://developer.android.com/develop/ui/compose/side-effects#rememberupdatedstate
     */
    val currentOnSystemEvent by rememberUpdatedState(onSystemEvent)

    DisposableEffect(context, systemActions) {
        val intentFilter = IntentFilter().apply {
            systemActions.forEach { addAction(it) }
        }
        val broadcast = object : BroadcastReceiver() {
            override fun onReceive(ctx: Context?, intent: Intent?) {
                currentOnSystemEvent(intent)
            }
        }
        context.registerReceiver(broadcast, intentFilter)
        onDispose {
            context.unregisterReceiver(broadcast)
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun ComponentAwareBatteryState() {
    val context = LocalContext.current
    SystemBroadcastReceiver(
        listOf(
            Intent.ACTION_BATTERY_CHANGED,
            Intent.ACTION_BATTERY_LOW,
            Intent.ACTION_BATTERY_OKAY,
            Intent.ACTION_POWER_CONNECTED,
            Intent.ACTION_POWER_DISCONNECTED
        ),
        onSystemEvent = context::processIntentAction
    )

    Text(
        modifier = Modifier
            .statusBarsPadding()
            .padding(3.dp)
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        textAlign = TextAlign.Center,
        text = "Aguardando o estado da bateria ..."
    )
}

private fun Context.processIntentAction(intent: Intent?) {
    when (intent?.action) {
        Intent.ACTION_BATTERY_CHANGED -> determineCurrentBatteryLevel(intent)
        Intent.ACTION_BATTERY_LOW -> batteryStatus(intent)
        Intent.ACTION_BATTERY_OKAY -> batteryStatus(intent)
        Intent.ACTION_POWER_CONNECTED -> determineCurrentBatteryLevel(intent)
        Intent.ACTION_POWER_DISCONNECTED -> determineCurrentBatteryLevel(intent)
    }
}

private fun Context.determineCurrentBatteryLevel(intent: Intent?) {
    val level = intent?.getIntExtra("level", -1) ?: -1
    val scale = intent?.getIntExtra("scale", -1) ?: -1
    val batteryPct = level * 100 / scale.toFloat()
    Toast.makeText(
        this,
        "Battery level: $batteryPct%",
        Toast.LENGTH_SHORT
    ).show()

    Timber.tag("battery_level").d("Battery level: $batteryPct%")
}


private const val BROADCAST_RECEIVER = "broadcast_receiver"

private fun batteryStatus(intent: Intent?) {
    val chargePlug: Int = intent?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) ?: -1
    val usbCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
    val acCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_AC

    Timber.tag(BROADCAST_RECEIVER).d(
        "Charge Plug: $chargePlug\n" +
                "USB Charge: $usbCharge\nAC Charge: $acCharge"
    )

    when (chargePlug) {
        BatteryManager.BATTERY_PLUGGED_USB -> Timber.tag(BROADCAST_RECEIVER).d("USB Charging")
        BatteryManager.BATTERY_PLUGGED_AC -> Timber.tag(BROADCAST_RECEIVER).d("AC Charging")
        else -> Timber.tag(BROADCAST_RECEIVER).d("Not Charging")
    }

    val status: Int = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
    val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
            || status == BatteryManager.BATTERY_STATUS_FULL
    Timber.tag(BROADCAST_RECEIVER).d(
        "Intent: $intent\n" +
                "Is Charging: $isCharging\nStatus: $status"
    )
}