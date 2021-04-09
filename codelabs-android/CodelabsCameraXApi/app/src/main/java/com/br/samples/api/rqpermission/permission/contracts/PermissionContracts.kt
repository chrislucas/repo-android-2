package com.br.samples.api.rqpermission.permission.contracts

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.multiplesContracts(
    permission: Array<String>,
    fn: (Map<String, Boolean>) -> Unit
) {
    /**
     *
     * https://developer.android.com/reference/androidx/activity/result/contract/ActivityResultContracts.RequestMultiplePermissions
     * A explicacao sobre como pedir
     * */

    val result: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions(), fn)

    // abrir o dialog perguntando sobre as permissoes solicitadas no array passado como argumento
    result.launch(permission)
}