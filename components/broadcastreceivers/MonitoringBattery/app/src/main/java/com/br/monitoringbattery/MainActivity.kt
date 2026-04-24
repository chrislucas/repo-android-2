package com.br.monitoringbattery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.br.monitoringbattery.ui.theme.MonitoringBatteryTheme

/*
    https://developer.android.com/training/monitoring-device-state/battery-monitoring
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MonitoringBatteryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier
                            .padding(innerPadding)
                            .statusBarsPadding()
                    )

                    SystemBroadcastReceiver(
                        systemActions = listOf(
                            Intent.ACTION_BATTERY_CHANGED,
                            Intent.ACTION_BATTERY_LOW,
                            Intent.ACTION_BATTERY_OKAY,
                            Intent.ACTION_POWER_CONNECTED,
                            Intent.ACTION_POWER_DISCONNECTED
                        ),
                        onSystemEvent = ::processIntentAction
                    )
                }
            }
        }
    }
}

private fun Context.processIntentAction(intent: Intent?) {
    when (intent?.action) {
        Intent.ACTION_BATTERY_CHANGED -> determineCurrentBatteryLevel(intent)
        Intent.ACTION_BATTERY_LOW -> Toast.makeText(this, "Battery low", Toast.LENGTH_SHORT).show()
        Intent.ACTION_BATTERY_OKAY -> Toast.makeText(this, "Battery okay", Toast.LENGTH_SHORT).show()
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

    Log.d("battery_level", "Battery level: $batteryPct%")
}

/*
    https://developer.android.com/training/monitoring-device-state/battery-monitoring
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
        val intentFilter = IntentFilter()
            .apply {
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = Bold
        )
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MonitoringBatteryTheme {
        Greeting(
            "Android", Modifier
                .fillMaxSize()
                .statusBarsPadding()
        )
    }
}
