package com.br.funwithdatabinding.view.features.tutorials.google.interactwithotherapps.activityresultapis

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.databinding.ActivityGetResultFromBinding
import com.br.funwithdatabinding.view.features.tutorials.google.interactwithotherapps.activityresultapis.SimpleResultActivity.Companion.KEY_RESULT
import com.br.funwithdatabinding.view.features.tutorials.google.interactwithotherapps.activityresultapis.SimpleResultActivityContract.Companion.KEY_INPUT
import timber.log.Timber

/**
    TODO
    Get a result from an activity
    https://developer.android.com/training/basics/intents/result

Registrar um callback para um resultado de atividade
https://developer.android.com/training/basics/intents/result?hl=pt-br#register

- Ao iniciar uma activity é possivel que ela seja destruida devido a pouca memoria disponivel.
Em casos de operacoes que consomen muita memoria, como uso de camera, a probabilidade é alta.

- Por isso, as APIs activity result dissociam o callback de resultado do local onde iniciamos
a proxima Activity.

- Como o callback precisa estar disponivel quando a Activity que iniciou o processo for recriada,
eh necessaior que esse callback seja registrado incondicionalmente, sempre que a primeira Activity
que inicia a próxima Activity for criada

- Em classes ComponentActivity ou Fragment, a API fornece o método
- registerForActivityResult() para registrar o resultado do callback
- registerForActivityResult() recebe uma ActivityResultContract e uma ActivityResultCallback
- e retorna um ActivityResultLauncher
 ** @see androidx.activity.result.contract.ActivityResultContracts
 * Define o tipo de entrada necessario para produzir um resultado do
 ** @see androidx.activity.result.ActivityResultCallback
 * É uma interface de métod0 Unico: omActivityResult
 ** @see androidx.activity.result.ActivityResultLauncher
 *

- A api fornce alguns contratos padroes para acoes basicas
- https://developer.android.com/reference/androidx/activity/result/contract/ActivityResultContracts
- Podemos criar contratos customizados
- https://developer.android.com/training/basics/intents/result?hl=pt-br#custom


 */
class GetResultFromActivity : AppCompatActivity() {

    private val binding: ActivityGetResultFromBinding by lazy {
        ActivityGetResultFromBinding.inflate(layoutInflater)
    }

    private val startActivityForResultWithCustomContract = registerForActivityResult(
        SimpleResultActivityContract(SimpleResultActivity::class.java)
    ) { result ->
        Timber.tag(TAG_VALID_INPUT).i("${result == VALID_INPUT}")
    }

    private val startActivityForResultContract = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val output = result.data?.getIntExtra(KEY_RESULT, -1)
            Timber.tag(TAG_VALID_INPUT).i("${output == VALID_INPUT}")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.root.run {
            setContentView(this)
            ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            binding.btnStartNewActivityResultsApi.setOnClickListener { _ ->
                startActivityForResultWithCustomContract.launch(VALID_INPUT)
            }

            binding.btnStartNewActivityResultsApiIi.setOnClickListener { _ ->
                val intent = Intent(context, SimpleResultActivity::class.java).apply {
                    putExtra(KEY_INPUT, VALID_INPUT)
                }
                startActivityForResultContract.launch(intent)
            }
        }
    }

    companion object {
        const val VALID_INPUT = 123
        const val TAG_VALID_INPUT = "TAG_VALID_INPUT"
    }
}

/*
    Criar um contrato personalizado
    https://developer.android.com/training/basics/intents/result?hl=pt-br#custom
 */

class SimpleResultActivityContract(private val clazz: Class<*>) :
    ActivityResultContract<Int, Int?>() {
    override fun createIntent(
        context: Context,
        input: Int
    ): Intent {
        return Intent(context, clazz).apply {
            putExtra(KEY_INPUT, input)
        }
    }

    override fun parseResult(resultCode: Int, result: Intent?): Int? {
        return if (resultCode == Activity.RESULT_OK) {
            result?.getIntExtra(KEY_RESULT, EMPTY_RESULT)
        } else {
            null
        }
    }

    companion object {
        const val KEY_INPUT = "KEY_INPUT"
        const val EMPTY_RESULT = -1
    }
}