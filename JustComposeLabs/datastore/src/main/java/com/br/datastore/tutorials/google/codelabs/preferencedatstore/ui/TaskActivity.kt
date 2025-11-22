package com.br.datastore.tutorials.google.codelabs.preferencedatstore.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.datastore.databinding.ActivityTaskBinding

/**
    http://developer.android.com/codelabs/android-preferences-datastore#2
    https://github.com/android/codelab-android-datastore/tree/preferences_datastore

    O app mostra uma lista de tarefas
        - A tarefa tem
            - nome
            - data limite
            - prioridade
            - uma flag que diz se foi encerrada ou nao
     ** @see com.br.datastore.tutorials.google.codelabs.preferencedatstore.data.Task

   -  Podemos executar duas acoes no app
        - Mostrar ou esconder a visibilidade de tasks completadas. Por padrao as tasks estao escondidas
        - Ordenar tasks por
            - prioridade
            - deadline
            - por deadline e prioridade

    estrutura
        - data
            - Task model
            - TaskRepository: Resposavel por prover tasks. Nesse projeto devolvemos um mock
    de lista de tarefas para fins praticos, atraves de um flow
            - User[Preferences|DataStore]Repository
                - Define um enum chamado SortOrder
                - A forma de ordenacao escolhida é guardada num SharedPreference | DataStore como
                    uma string, baseado no valor do nome do enum. O SharedPreference expoe
                    metodos sincronos
                - Expoe metodos de save e get Sincronos
        - ui
            - Activity e recycleview
            - ViewModel
                - TaskViewModel: UI Logic
                    - Contem todos os elementos para construir o Dado necessario para mostrar a UI
                        - Lista de tasks
                        - showCompleted and sortOrder flags (wrapped in TasksUiModel)
                            - Toda vez que um desses valores mudar, reconstruimos uma nova TasksUiModel
                            para isso combinamos 3 elementos
                                - Uma Flow<List<Tasks>> é retornada do Repository
                                - MutableStateFlow<Boolean>, armazena o ultimo estado da variavel showCompleted
                                - MutableSateFlow<SortOrder>, armazena o ultimo estado da variavel sortOrder

            - Adapter/RecyclerView
 */
class TaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTaskBinding.inflate(layoutInflater)
        binding.root.run {
            setContentView(this)
            ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(
                    systemBars.left, systemBars.top, systemBars.right, systemBars.bottom
                )
                insets
            }
        }
    }

}