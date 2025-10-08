package com.br.justcomposelabs.tutorial.google.eventhandling.localbroadcastmanager

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
import com.br.justcomposelabs.R
import com.br.justcomposelabs.databinding.ActivityEventHandlingLocalBrodcastManagerBinding

/*
    https://kalpeshchandora12.medium.com/event-handling-with-localbroadcastmanager-android-b62ae5759b5e
 */
class EventHandlingLocalBroadcastManagerActivity : AppCompatActivity() {

    private val broadcastEvent: BroadcastEvent by lazy {
        BroadcastEvent(this) { intent ->
            intent?.extras?.let { bundle ->
                val data = bundle.getString(EXTRA_STRING) ?: "empty extras"
                Log.i("EVENT_HANDLING", data)
            }
        }
    }

    private val binding: ActivityEventHandlingLocalBrodcastManagerBinding by lazy {
        ActivityEventHandlingLocalBrodcastManagerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val ctx = this
        with(binding) {
            setContentView(root)
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(
                    systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom
                )
                insets
            }

            mockSendExtras.setOnClickListener {
                val bundle = Bundle().apply {
                    putString(EXTRA_STRING, "hello world local broadcast receiver")
                }
                broadcastEvent.sendEvent(ctx, bundle)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        broadcastEvent.dispose()
    }

    companion object {
        private const val EXTRA_STRING = "EXTRA_STRING"
    }
}


abstract class EventBroadcastManager(private val block: (Intent?) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { block(intent) }
    }
}


class BroadcastEvent(private val context: Context, block: (Intent?) -> Unit) :
    EventBroadcastManager(block) {
    init {
        LocalBroadcastManager.getInstance(context).registerReceiver(
            this, IntentFilter(ACTION)
        )
    }

    fun sendEvent(context: Context, bundle: Bundle) {
        val data = Intent(ACTION).apply {
            putExtra(EVENT_MOCK, "mock")
            putExtras(bundle)
        }

        LocalBroadcastManager.getInstance(context).sendBroadcast(data)
    }


    fun dispose() {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(
            this
        )

        Log.i("EVENT_HANDLING", "dispose")
    }

    companion object {
        val ACTION = "BROADCAST_EVENT"
        private val EVENT_MOCK = "EVENT_MOCK"
    }
}