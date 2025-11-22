package com.br.justcomposelabs.tutorial.google.localbroadcastmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.br.justcomposelabs.databinding.ActivityLocalBroadcastManagerBinding

/*
    https://www.geeksforgeeks.org/android/how-to-use-localbroadcastmanager-in-android/
 */
class LocalBroadcastManagerActivity : AppCompatActivity() {

    private val binding: ActivityLocalBroadcastManagerBinding by lazy {
        ActivityLocalBroadcastManagerBinding.inflate(layoutInflater)
    }

    private val broadcastReceiver: CustomBroadcastReceiver by lazy {
        CustomBroadcastReceiver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val ctx = this
        LocalBroadcastManager.getInstance(ctx).registerReceiver(
            broadcastReceiver, IntentFilter(CustomBroadcastReceiver.INTENT_FILTER)
        )

        binding.run {
            setContentView(root)
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            mockSendExtras.setOnClickListener {
                val data = Intent(CustomBroadcastReceiver.INTENT_FILTER).apply {
                    putExtras(
                        Bundle().apply {
                            putString(CustomBroadcastReceiver.EXTRA_STRING, "hello world local broadcast receiver")
                        }
                    )
                }

                LocalBroadcastManager.getInstance(ctx).sendBroadcast(data)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }
}


class CustomBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.extras?.let { bundle ->
            val message = bundle.getString(EXTRA_STRING) ?: "empty message"
            Log.i("EVENT_HANDLING", message)
        }
    }

    companion object {
        const val EXTRA_STRING = "EXTRA_STRING"
        const val INTENT_FILTER = "INTENT_FILTER"
    }
}