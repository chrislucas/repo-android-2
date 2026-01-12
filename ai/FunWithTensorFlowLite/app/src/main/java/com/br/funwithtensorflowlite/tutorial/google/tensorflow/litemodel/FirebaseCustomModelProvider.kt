package com.br.funwithtensorflowlite.tutorial.google.tensorflow.litemodel

import com.google.firebase.ml.common.modeldownload.FirebaseModelManager
import com.google.firebase.ml.custom.FirebaseCustomLocalModel
import com.google.firebase.ml.custom.FirebaseCustomRemoteModel
import com.google.firebase.ml.custom.FirebaseModelInterpreter
import com.google.firebase.ml.custom.FirebaseModelInterpreterOptions
import com.google.firebase.ml.modeldownloader.CustomModelDownloadConditions
import com.google.firebase.ml.modeldownloader.DownloadType
import com.google.firebase.ml.modeldownloader.FirebaseModelDownloader
import org.tensorflow.lite.Interpreter

/*
    https://firebase.google.com/docs/ml/android/use-custom-models#2.-download-the-model-to-the-device-and-initialize-a-tensorflow-lite-interpreter
 */

object FirebaseCustomModelProvider {


    fun loadingDigitModel(onSuccess: (Interpreter) -> Unit, onFailure: (Exception) -> Unit) {
        CustomModelDownloadConditions.Builder()
            .requireWifi()
            .build().let { conditions ->

                FirebaseModelDownloader
                    .getInstance()
                    .getModel(
                        RECOGNIZE_DIGIT_MODEL,
                        DownloadType.LOCAL_MODEL_UPDATE_IN_BACKGROUND,
                        conditions
                    )
                    .addOnSuccessListener { model ->
                        model.file?.let { file ->
                            onSuccess(Interpreter(file))
                        }
                    }

                    .addOnFailureListener(onFailure)
            }
    }


    /*
        Use a TensorFlow Lite model for inference with ML Kit on Android
        https://firebase.google.com/docs/ml-kit/android/use-custom-models#kotlin_2

        2. Create a TensorFlow Lite interpreter instead of a FirebaseModelInterpreter
        https://firebase.google.com/docs/ml/android/migrate-from-legacy-api#2_create_a_tensorflow_lite_interpreter_instead_of_a_firebasemodelinterpreter
     */

    private fun getModel(modelName: String = "first_model", callback: (Interpreter) -> Unit) {
        val remoteModel = FirebaseCustomRemoteModel.Builder(modelName).build()
        FirebaseModelManager.getInstance().getLatestModelFile(remoteModel)
            .addOnCompleteListener { task ->
                val modelFile = task.getResult()
                if (modelFile != null) {
                    // Instantiate an org.tensorflow.lite.Interpreter object.
                    callback(Interpreter(modelFile))
                }
            }
    }

    private fun initLocalModel(modelPathName: String = "assets/mnist.tflite") {
        val localModel = FirebaseCustomLocalModel.Builder()
            .setAssetFilePath(modelPathName)
            .build()




        val options = FirebaseModelInterpreterOptions.Builder(localModel).build()
        val interpreter = FirebaseModelInterpreter.getInstance(options)
    }

    private const val RECOGNIZE_DIGIT_MODEL = "first_model"
}