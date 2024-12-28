package com.br.funwithcamerax.tutorials.codelabs

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ImageCapture
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithcamerax.R
import com.br.funwithcamerax.databinding.ActivityGettingStartingCameraXactivityBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/*
    TODO
    https://developer.android.com/codelabs/camerax-getting-started#1

    O link do tutorial acima consiste em usar:
    - CameraX PreviewView - Para visualizar a camera imagem/video
    - Botao padrao para controlar captura de imagem
    - botao padrao para iniciar/puasar captura de video
    - um guia vertical para posicionar 2 botoes

    About Camera X
    https://developer.android.com/media/camera/camerax

    Architecture
    https://developer.android.com/media/camera/camerax/architecture

    Config
    https://developer.android.com/media/camera/camerax/configuration

    use cases
    https://developer.android.com/media/camera/camerax/preview
 */


/*
    SetUp Activity
    -
    Request The necessary permissions
    -  Antes de abrir a camera o app necessita da Permissao de microfone para gravar
    audio, isso é exigido a partir do Andreid 9
 */
typealias LumaListener = (luma: Double) -> Unit

class GettingStartingCameraXActivity : AppCompatActivity() {

    private val binding: ActivityGettingStartingCameraXactivityBinding by lazy {
        ActivityGettingStartingCameraXactivityBinding.inflate(layoutInflater)
    }

    private var imageCapture: ImageCapture? = null
    private var videoCapture: VideoCapture<Recorder>? = null
    private var recording: Recording? = null
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setOnApplyWindowInsetsListener()
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissions()
        }

        binding.run {
            this.imageCaptureButton.setOnClickListener { _ -> takePhoto() }
            this.videoCaptureButton.setOnClickListener { _ -> captureVideo() }
        }
        initExecutor()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private fun initExecutor() {
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun setOnApplyWindowInsetsListener() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun takePhoto() {}

    private fun captureVideo() {}

    private fun startCamera() {}

    private fun requestPermissions() {}

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }
}