package com.br.funwithdatabinding.view.features.funwithdatabinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

/**
 * https://medium.com/@myofficework000/data-binding-binding-adapters-6b0b717121e1
 */
object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:image")
    fun loadImage(view: ImageView, url: String?) {
        url?.let {
            Picasso.get().load(it).into(view)
        }
    }

}