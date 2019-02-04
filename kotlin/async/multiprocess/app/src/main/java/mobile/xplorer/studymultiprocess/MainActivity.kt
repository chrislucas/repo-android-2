package mobile.xplorer.studymultiprocess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.HandlerThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handlerThread = HandlerThread("Test")
    }
}
