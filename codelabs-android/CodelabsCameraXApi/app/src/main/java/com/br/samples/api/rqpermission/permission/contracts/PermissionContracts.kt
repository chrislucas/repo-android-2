package com.br.samples.api.rqpermission.permission.contracts

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.br.samples.api.rqpermission.permission.registers.CallbacksOfContractsRequestPermission.getActivityResultCallbackMultipleRequests
import com.br.samples.api.rqpermission.permission.registers.CallbacksOfContractsRequestPermission.getActivityResultCallbackSingleRequest


/**
 *
 * https://developer.android.com/reference/androidx/activity/result/contract/ActivityResultContracts.RequestMultiplePermissions
 * A explicacao sobre como pedir
 * */


fun <R> AppCompatActivity.multiplesPermissionsContract(
    permission: Array<String>,
    fn: (Map<String, Boolean>) -> R
) {
    val result: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
            getActivityResultCallbackMultipleRequests(fn)
        )

    // abrir o dialog perguntando sobre as permissoes solicitadas no array passado como argumento
    result.launch(permission)
}

fun <R> AppCompatActivity.singlePermissionContract(permission: String, fn: (Boolean) -> R) {

    /**
     * 1) no metodo de inicializacao da activity ou fragment chame o metodo
     * registerForActivityResult(ActivityResultContract<I, O> contract
     * , ActivityResultCallback<O> callback)
     * e passe uma instancia de ActivityResultContract e uma implementacao
     * de ActivityResultCallback
     *
     *      Para pedir permissao a algum recurso do dispositivo ja existe um contrato/implementacao
     *      definido na SDK do android ActivityResultContracts.RequestPermission()
     *
     * */

    val result: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
        getActivityResultCallbackSingleRequest(fn)
    )

    result.launch(permission)
}