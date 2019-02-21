package mobile.xplorer.studymultiprocess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.core.os.postDelayed
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class ActivitySampleRecursiveHandler : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_recursive_handler)

        
        val simpleFormatDate = SimpleDateFormat("kk:mm:ss a", Locale.getDefault())
        val textViewDateNow = findViewById<TextView>(R.id.text_view_date_now)
        val handler = Handler()

        val r = object : Runnable {
            override fun run() {
                val now = simpleFormatDate.format(Date(Calendar.getInstance().timeInMillis))
                Log.i("DATE", now)
                handler.postDelayed(this, 1000)
                textViewDateNow.text = now
            }
        }
        handler.postDelayed(r, 0)
    }
}
