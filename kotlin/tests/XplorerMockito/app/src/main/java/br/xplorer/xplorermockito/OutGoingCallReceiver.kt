package br.xplorer.xplorermockito

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class OutGoingCallReceiver : BroadcastReceiver() {

    companion object {
        const val BUNDLE_PHONE_NUMBER = "BUNDLE_PHONE_NUMBER"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action.equals(Intent.ACTION_NEW_OUTGOING_CALL, true)) {
            val phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(BUNDLE_PHONE_NUMBER, phoneNumber)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}
