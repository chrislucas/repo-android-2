package com.br.funwithdatabinding.view.features.funwithdatabinding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.br.funwithdatabinding.R
import com.br.funwithdatabinding.databinding.ActivityFunWithDatabindingBinding


/**
 * Tutorial
 * https://medium.com/@myofficework000/data-binding-binding-adapters-6b0b717121e1
 *
 * Pontos positivos levantados pelo autor
 *  - Reduzir codigo repetitivo: Nao precisamos usar o findView para
 *  fazer o binding do xml com o codigo e acessar o componente visual
 *
 *  - Aumenta a performance: DataBinging minimiza a necessidade de chamadas
 *  ao framework UI
 *
 *  - Simplfica manutencao de codigo: O codigo da UI se torna mais simples
 *
 *
 *  Vamos implementar esse tutorial e analisar criticamente essas afirmacoes acima.
 *
 *  O projeto é simplismente baixar uma imagem de uma api e mostra-la na tela
 *  atraves do data-binding.
 *  Vamos usar retrofit para requisição de rede e LiveData
 *
 *  Artigo de um ano antes deizendo porque nao usar databiding
 *  https://callmeryan.medium.com/why-we-should-stop-using-data-binding-in-android-cf3c439637c
 *
 * Google docs
 * https://developer.android.com/topic/libraries/data-binding
 *
 * Binding adapters
 * https://developer.android.com/topic/libraries/data-binding/binding-adapters
 *
 *
 */
class FunWithDataBindingActivity : AppCompatActivity() {

    private lateinit var bindingActivity: ActivityFunWithDatabindingBinding
    private lateinit var viewModel: DogImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        bindingActivity = DataBindingUtil.setContentView(
            this,
            R.layout.activity_fun_with_databinding
        )

        bindingActivity.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[DogImageViewModel::class.java]
        bindingActivity.viewmodel = viewModel
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recycler_view_all_features)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}