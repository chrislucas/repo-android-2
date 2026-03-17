package com.br.opencamerafromwebview.tutorialiv

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.webkit.CookieManager
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.opencamerafromwebview.web.viewclient.CustomWebViewClient
import com.br.opencamerafromwebview.web.chromeclient.WebChromeClientFileChooser
import com.br.opencamerafromwebview.databinding.ActivityOpenCameraFromWebViewBinding

/**
https://stackoverflow.com/questions/60561168/why-i-am-not-able-to-open-camera-from-webview-in-android

What's the difference between setWebViewClient vs. setWebChromeClient?
https://stackoverflow.com/questions/2835556/whats-the-difference-between-setwebviewclient-vs-setwebchromeclient
 * @see CallbackProxy

// https://android.googlesource.com/platform/frameworks/base.git/+/android-4.3_r2.1/core/java/android/webkit/CallbackProxy.java

// Instance of WebViewClient that is the client callback.
private volatile WebViewClient mWebViewClient;

// Instance of WebChromeClient for handling all chrome functions.
private volatile WebChromeClient mWebChromeClient;

- WebChromeClient
Permite manipular recursos javascript, favicons, titles e progresso
de carregamento de pagina.

- WebViewClient
Caso estejamos trabalhanod numa simples página web que carrega um HTML,
recomenda-se usar WebViewClient

Obviamente essas interfaces tem propósitos diferentes e não são mutuamente excludentes.

WebChromeClient permite manipular JS como abrir uma Dialog como se fora um Alert Javascript,
WebViewClient possui eventos callbacks como onPageFinished e shouldOverrideUrlLoading
WebChromeClient permite monitorar o percentual de carregamento da página

Are WebViewClient and WebChromeClient mutually exclusive?
https://stackoverflow.com/questions/6474768/are-webviewclient-and-webchromeclient-mutually-exclusive
 */


class OpenCameraFromWebViewActivity : AppCompatActivity() {

    private val binding: ActivityOpenCameraFromWebViewBinding by lazy {
        ActivityOpenCameraFromWebViewBinding.inflate(layoutInflater)
    }

    private val webChromeClientFileChooser = WebChromeClientFileChooser(
        this,
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            getResults(
                result.resultCode,
                result.data,
            )
        }
    )

    private val customWebViewClient = CustomWebViewClient(this@OpenCameraFromWebViewActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.run {
            setContentView(root)
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            checkPermissionsNeeded()
        }
    }


    private fun initWebView() {
        binding.webViewWithCamera.run {
            settings.setSupportZoom(true)
            settings.javaScriptEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            settings.domStorageEnabled = true
            settings.allowContentAccess = true
            webChromeClient = webChromeClientFileChooser
            //webViewClient = customWebViewClient
            with(CookieManager.getInstance()) {
                //setCookie("https://www.mercadolivre.com.br", "melilab=test")
                //setAcceptThirdPartyCookies(this@run, true)
            }
            //loadUrl("https://www.mercadolivre.com.br/upload-recetas")
            //loadUrl("https://stackoverflow.com/questions/53984725/networksecurityconfig-no-network-security-config-specified-using-platform-defa")
            //loadData(readRaw(R.raw.index), "text/html", "utf-8")
            loadData(UploadFile.html, "text/html", "utf-8")
        }
    }

    private fun checkPermissionsNeeded() {
        val allPermissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
        )
        val hasCameraPermission = allPermissions.map(::checkSelfPermission)
            .all {
                it == PackageManager.PERMISSION_GRANTED
            }
        if (hasCameraPermission) {
            initWebView()
            return
        }

        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.values.all { it }.let { flag ->
                if (flag) {
                    initWebView()
                }
            }
        }.launch(allPermissions)
    }

    fun getResults(
        resultCode: Int,
        data: Intent?
    ) {
        var results: Array<Uri> = emptyArray()
        if (resultCode == RESULT_OK) {
            if (data != null) {
                data.clipData?.let { cd ->
                    results = Array<Uri>(cd.itemCount) { Uri.EMPTY }
                    for (i in 0 until cd.itemCount) {
                        results[i] = cd.getItemAt(i).uri
                    }
                } ?: run {
                    results = data.data?.let { uri -> arrayOf(uri) } ?: emptyArray()
                }
            } else {
                if (webChromeClientFileChooser.cameraImagePath != null) {
                    results = arrayOf(Uri.parse(webChromeClientFileChooser.cameraImagePath))
                }
            }
            /*
                TODO iterar pela lista de URIS e inserir numa lista
             */
            webChromeClientFileChooser.imagePathCallback?.onReceiveValue(results)
        }
        webChromeClientFileChooser.imagePathCallback = null
    }


    object UploadFile {
        /*
            https://talkjs.com/resources/chat-file-upload-android/
         */
        val html = """
            <!DOCTYPE html>
            <html lang="en">
                <head>
                    <meta http-equiv="content-type" content="text/html; charset=utf-8">
                    <title>Test</title>
                </head>
                <body>
                    <div id="talkjs-container" style="width: 100%; height: 600px">
                        Olá, mundo
                        <input type="file" id="avatar" name="avatar" accept="image/png, image/jpeg" />
                    </div>
                </body>
            </html>
            """.trimIndent()
    }
}