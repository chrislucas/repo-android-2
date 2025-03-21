package com.br.opencamerafromwebview.web.viewclient

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class CustomWebViewClient(private val context: Context) : WebViewClient() {

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        Toast.makeText(context, "Erro ao abrir WebClient", Toast.LENGTH_SHORT)
            .show();
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        Log.i(TAG, "ON_PAGE_STARTED")
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        Log.i(TAG, "ON_PAGE_FINISHED")
    }

    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        Log.i(TAG, "ON_PAGE_FINISHED ${view?.url}")
        return super.shouldOverrideUrlLoading(view, request)
    }

    companion object {
        private const val TAG = "WEB_VIEW_CLIENT"
    }
}