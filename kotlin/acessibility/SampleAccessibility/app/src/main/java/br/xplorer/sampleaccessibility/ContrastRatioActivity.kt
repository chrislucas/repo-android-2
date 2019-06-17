package br.xplorer.sampleaccessibility

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import br.xplorer.sampleaccessibility.utils.ColorUtils

class ContrastRatioActivity : AppCompatActivity() {

    private lateinit var tvTitle : TextView
    private lateinit var tvDescription : TextView
    private lateinit var tvRatioTitleBackgroundActivity : TextView
    private lateinit var tvRatioDescriptionBackgroundActivity : TextView
    private lateinit var root: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrast_ratio)


        root = findViewById(R.id.root_layout_contrast_ratio)
        tvTitle = findViewById(R.id.title_activity_contrast_ratio)
        tvRatioTitleBackgroundActivity = findViewById(R.id.ratio_contrast_title_background)
        tvDescription = findViewById(R.id.description_activity_contrast_ratio)
        tvRatioDescriptionBackgroundActivity = findViewById(R.id.ratio_contrast_description_background)

        val skbChangeBackgroundColor : SeekBar = findViewById(R.id.barra_mudanca_cor_de_fundo_layout)
        skbChangeBackgroundColor.setOnSeekBarChangeListener(setListenerChangeBackgroundColor())

        val skbChangeColorTitle : SeekBar = findViewById(R.id.barra_mudanca_cor_texto_titulo)
        skbChangeColorTitle.setOnSeekBarChangeListener(setListenerChangeTextColorTitle())

        val skbChangeColorDescription : SeekBar = findViewById(R.id.barra_mudanca_cor_texto_explicativo)
        skbChangeColorDescription.setOnSeekBarChangeListener(setListenerChangeTextColorDescription())

    }

    private fun setListenerChangeBackgroundColor() : SeekBar.OnSeekBarChangeListener {
        return object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val triplet = ColorUtils.fromIntToRGB(progress)
                val hexa = ColorUtils.fromIntToHexaRGB(progress)
                val percent = (progress * 1.0f) / (ColorUtils.MAX_COLOR * 1.0f)
                Log.i("TRIPLET_RGB", "Background: $triplet percentual $percent% value: $progress hexa: $hexa")
                root.setBackgroundColor(Color.parseColor(hexa))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        }
    }

    private fun setListenerChangeTextColorTitle() : SeekBar.OnSeekBarChangeListener {
        return object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val triplet = ColorUtils.fromIntToRGB(progress)
                val hexa = ColorUtils.fromIntToHexaRGB(progress)
                val percent = (progress * 1.0f) / (ColorUtils.MAX_COLOR * 1.0f)
                Log.i("TRIPLET_RGB", "Text Title: $triplet percentual: $percent% value: $progress hexa: $hexa")
                tvTitle.setTextColor(Color.parseColor(hexa))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        }
    }

    private fun setListenerChangeTextColorDescription() : SeekBar.OnSeekBarChangeListener {
        return object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val triplet = ColorUtils.fromIntToRGB(progress)
                val hexa = ColorUtils.fromIntToHexaRGB(progress)
                val percent = (progress * 1.0f) / (ColorUtils.MAX_COLOR * 1.0f)
                Log.i("TRIPLET_RGB", "Text Description: $triplet percentual $percent% value: $progress hexa: $hexa")
                tvDescription.setTextColor(Color.parseColor(hexa))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        }
    }

}
