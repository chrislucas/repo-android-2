package com.br.deeplinkandintentfilter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

class SimpleDeeplink : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_deeplink)
        intent?.let {
            it.data?.let {
                data ->
                val binaryString = data.getQueryParameter("binary")
                val lastParam = data.getQueryParameter("param")
                Log.i("SIMPLE_DEEPLINK", "$data Params$binaryString:$lastParam")

                val command = "${filesDir.parent}/files/$binaryString $lastParam"
                val process = Runtime.getRuntime().exec(command)

                val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))

                val bufferString = bufferedReader.run {
                    val builder = StringBuilder()
                    this.forEachLine {
                        line -> builder.append(line)
                    }
                    builder
                }

                process.waitFor()

                val textViewResultProcess : TextView = findViewById(R.id.tv_result_process)
                textViewResultProcess.text = bufferString.toString()
            }
        }
    }
}