package br.com.xplorer.drawablecolormanipulation


import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layout = findViewById<LinearLayout>(R.id.wrapper_layout_message)
        val options = MainActivity.mapPorterDuff.keys.toTypedArray()
        val spinnerPotterDuffModes = findViewById<Spinner>(R.id.potter_duff_modes)
        spinnerPotterDuffModes.run {
            this.adapter = ArrayAdapter(
                context
                , android.R.layout.simple_list_item_1
                , options
            )
            this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val drawable = layout.background
                    drawable?.setColorFilter(ContextCompat.getColor(baseContext, R.color.light_blue)
                        , mapPorterDuff[options[position]] ?: PorterDuff.Mode.DST_ATOP)
                    layout.background = drawable
                }
            }
        }

        layout.run {
            val drawable = ContextCompat.getDrawable(context, R.drawable.bubble_chat_recipient)
            // https://developer.android.com/reference/android/graphics/PorterDuff.Mode
            drawable?.setColorFilter(ContextCompat.getColor(context, R.color.light_blue), PorterDuff.Mode.OVERLAY)
            this.background = drawable
        }
    }

    companion object {
        private val mapPorterDuff = ArrayMap<String, PorterDuff.Mode>()
        init {
            mapPorterDuff.put("PorterDuff.Mode.OVERLAY", PorterDuff.Mode.OVERLAY)
            mapPorterDuff.put("PorterDuff.Mode.MULTIPLY", PorterDuff.Mode.MULTIPLY)
            mapPorterDuff.put("PorterDuff.Mode.DST_ATOP", PorterDuff.Mode.DST_ATOP)
            mapPorterDuff.put("PorterDuff.Mode.DST", PorterDuff.Mode.DST)
            mapPorterDuff.put("PorterDuff.Mode.DARKEN", PorterDuff.Mode.DARKEN)
            mapPorterDuff.put("PorterDuff.Mode.DST_OUT", PorterDuff.Mode.DST_OUT)
            mapPorterDuff.put("PorterDuff.Mode.DST_IN", PorterDuff.Mode.DST_IN)
            mapPorterDuff.put("PorterDuff.Mode.DST_OVER", PorterDuff.Mode.DST_OVER)
            mapPorterDuff.put("PorterDuff.Mode.ADD", PorterDuff.Mode.ADD)
            mapPorterDuff.put("PorterDuff.Mode.CLEAR", PorterDuff.Mode.CLEAR)
            mapPorterDuff.put("PorterDuff.Mode.LIGHTEN", PorterDuff.Mode.LIGHTEN)
            mapPorterDuff.put("PorterDuff.Mode.XOR", PorterDuff.Mode.XOR)
        }
    }
}
