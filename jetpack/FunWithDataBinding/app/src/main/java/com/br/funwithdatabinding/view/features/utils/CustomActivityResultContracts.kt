package com.br.funwithdatabinding.view.features.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.util.CollectionUtils


/*
    TODO criar esse contrato para recuperar multiplas imagens da pasta
 */
class SelectMultiplesPicsContract : ActivityResultContract<Unit, Uri?>() {
    override fun createIntent(context: Context, input: Unit): Intent {
        val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
        contentSelectionIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        return contentSelectionIntent
    }

    override fun parseResult(
        resultCode: Int,
        intent: Intent?
    ): Uri? {
        return intent.takeIf { resultCode == Activity.RESULT_OK }?.data
    }
}

/*
    TODO criar contratos com essas intents comuns da documentacao
    Common intents
    https://developer.android.com/guide/components/intents-common?authuser=1
 */



fun AppCompatActivity.getPickVisualMediaRequestContract(max: Int, onSuccess: (List<Uri>) -> Unit, onError: () -> Unit)
        : ActivityResultLauncher<PickVisualMediaRequest> =
    registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(3)) { uris ->
        /*
            Fonte
            https://stackoverflow.com/questions/33604951/maximum-image-selection-limit-from-gallery-android
         */
        if (uris.size > 3) {
            onError()
        } else {
            onSuccess(uris)
        }
    }

