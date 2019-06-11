package br.xplorer.sampleaccessibility

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView

class ContrastRatio : AppCompatActivity() {


    lateinit var tvTitle : TextView
    lateinit var tvDescription : TextView

    lateinit var skbChangeBackgroundColor : SeekBar
    lateinit var skbChangeColorTitle : SeekBar
    lateinit var skbChangeColorDescription : SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrast_ratio)


    }
}
