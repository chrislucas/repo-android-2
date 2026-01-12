package com.br.justcomposelabs.tutorial.customview.loadoverlaycustomview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.br.justcomposelabs.R
import timber.log.Timber

/*
    TODO criar um custom attr para que essa custom view tenha as seguintes caracteristicas
    - boolean: dismissible
        - ao tocar  no overlay ele desaparece
    - boolean: closeable
        - ser um overlay que desaparece sozinho

    Docs
    https://developer.android.com/develop/ui/views/layout/custom-views/create-view
    https://medium.com/@Zielony/guide-to-android-custom-views-attributes-ab28de3e54b7
 */
class LoadCustomViewOverlay @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val textLoading: TextView
    private val progressBar: ProgressBar

    private val viewOverlay: View


    init {
        LayoutInflater.from(context).inflate(
            R.layout.layout_custom_view_overlay, this, true
        )

        textLoading = rootView.findViewById<TextView>(R.id.text_centered)
        progressBar = rootView.findViewById<ProgressBar>(R.id.progress_bar)
        viewOverlay = rootView.findViewById<View>(R.id.overlay_view)
    }

    fun show() {
        textLoading.text = context.getString(R.string.loading_label)
        progressBar.visibility = VISIBLE
        viewOverlay.visibility = VISIBLE
    }

    fun hide() {
        textLoading.text = context.getString(R.string.main_content_below)
        progressBar.visibility = GONE
        viewOverlay.visibility = GONE
    }

    /*
        custom view lifecycle aware
        https://proandroiddev.com/make-your-custom-view-lifecycle-aware-its-a-piece-of-cake-90b7c0498686
     */


    private val lifecycleObserver = object : DefaultLifecycleObserver {

        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            Timber.tag("lifecycleObserver").d("onResume")
        }

        override fun onPause(owner: LifecycleOwner) {
            super.onPause(owner)
            Timber.tag("lifecycleObserver").d("onPause")
        }

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            Timber.tag("lifecycleObserver").d("onDestroy")
        }
    }

    fun registerLifecycleOwner(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
    }
}