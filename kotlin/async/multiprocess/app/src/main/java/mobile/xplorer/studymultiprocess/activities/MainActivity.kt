package mobile.xplorer.studymultiprocess.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import mobile.xplorer.studymultiprocess.R
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val handlerThread = HandlerThread("Test")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)
        val calendar = Calendar.getInstance()
        val formatDate = SimpleDateFormat("kk:mm:ss", Locale.getDefault())
        val runnable = Runnable {
            val now = formatDate.format(calendar.timeInMillis)
            Log.i("TIMER", now)
        }
        handler.postDelayed(runnable, 1000)
    }
}
