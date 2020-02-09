package br.xplorer.links

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class IncomingLink : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_link)

        val data : Uri? = intent?.data
        if (data != null) {
            Log.i("DATA", "$data")
        }
    }
}
