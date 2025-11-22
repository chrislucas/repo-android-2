package com.br.justcomposelabs.tutorial.cursor.uitreecomment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// ViewModel para gerenciar o estado da árvore de comentários
class CommentViewModel : ViewModel() {
    
    // Estado privado mutável
    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    
    // Estado público imutável
    val comments: StateFlow<List<Comment>> = _comments.asStateFlow()
    
    // Contador de IDs
    private var nextId = 1
    
    // Inicializar com comentários de exemplo
    init {
        initializeSampleComments()
    }
    
    // Adicionar comentário
    fun addComment(parentComment: Comment, author: String, content: String) {
        val newComment = Comment(
            id = nextId++,
            content = content,
            author = author,
            parentId = parentComment.id
        )
        
        _comments.update { currentComments ->
            // Encontra o comentário pai e adiciona a resposta
            val updatedComments = currentComments.toMutableList()
            updatedComments.forEach { comment ->
                comment.findComment(parentComment.id)?.addReply(newComment)
            }
            // Retorna nova lista para forçar recomposição
            updatedComments.toList()
        }
    }
    
    // Inicializar com dados de exemplo
    private fun initializeSampleComments() {
        _comments.value = listOf(
            // ========== THREAD 1: Discussão sobre Jetpack Compose ==========
            Comment(
                id = nextId++,
                content = "Alguém já testou o novo Jetpack Compose? Queria saber opiniões sobre a produtividade.",
                author = "DevAndroid",
                replies = mutableListOf(
                    Comment(
                        id = nextId++,
                        content = "Sim! Estou usando há 6 meses. A produtividade aumentou muito, especialmente com previews.",
                        author = "ComposeFan",
                        parentId = 1,
                        replies = mutableListOf(
                            Comment(
                                id = nextId++,
                                content = "As previews são realmente incríveis! Economizam muito tempo de build.",
                                author = "AndroidStudio",
                                parentId = 2
                            ),
                            Comment(
                                id = nextId++,
                                content = "Concordo! E com @PreviewParameter fica ainda melhor para testar diferentes estados.",
                                author = "UIDesigner",
                                parentId = 2,
                                replies = mutableListOf(
                                    Comment(
                                        id = nextId++,
                                        content = "Você usa @PreviewScreenSizes também? Ajuda muito com responsividade.",
                                        author = "ResponsiveUI",
                                        parentId = 4,
                                        replies = mutableListOf(
                                            Comment(
                                                id = nextId++,
                                                content = "Sim! Especialmente para tablets e foldables. Essencial hoje em dia.",
                                                author = "UIDesigner",
                                                parentId = 5,
                                                replies = mutableListOf(
                                                    Comment(
                                                        id = nextId++,
                                                        content = "Nível 5 de profundidade! Compose torna isso tudo possível de visualizar facilmente.",
                                                        author = "DeepThreadFan",
                                                        parentId = 6
                                                    )
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    ),
                    Comment(
                        id = nextId++,
                        content = "Ainda tenho dúvidas sobre performance em listas grandes. Alguém teve problemas?",
                        author = "MobileDev",
                        parentId = 1,
                        replies = mutableListOf(
                            Comment(
                                id = nextId++,
                                content = "LazyColumn funciona perfeitamente! É equivalente ao RecyclerView em performance.",
                                author = "PerformanceGuru",
                                parentId = 8
                            ),
                            Comment(
                                id = nextId++,
                                content = "Use remember e derivedStateOf para otimizar recomposições. Faz muita diferença!",
                                author = "OptimizationPro",
                                parentId = 8,
                                replies = mutableListOf(
                                    Comment(
                                        id = nextId++,
                                        content = "Excelente dica! E não se esqueça de usar key() no items do LazyColumn.",
                                        author = "ComposeMaster",
                                        parentId = 10
                                    )
                                )
                            )
                        )
                    ),
                    Comment(
                        id = nextId++,
                        content = "A curva de aprendizado é um pouco íngreme no início, mas vale muito a pena!",
                        author = "KotlinLover",
                        parentId = 1
                    )
                )
            ),
            
            // ========== THREAD 2: Material Design 3 ==========
            Comment(
                id = nextId++,
                content = "Material Design 3 está incrível! Os temas dinâmicos são o futuro do design mobile.",
                author = "DesignLead",
                replies = mutableListOf(
                    Comment(
                        id = nextId++,
                        content = "Concordo totalmente! A adaptação automática de cores é revolucionária.",
                        author = "ColorTheorist",
                        parentId = 13
                    ),
                    Comment(
                        id = nextId++,
                        content = "Alguém já implementou tema claro/escuro com Material 3? Como ficou a experiência?",
                        author = "ThemeExplorer",
                        parentId = 13,
                        replies = mutableListOf(
                            Comment(
                                id = nextId++,
                                content = "Sim! É muito simples com isSystemInDarkTheme(). Funciona out-of-the-box.",
                                author = "DarkModeEnthusiast",
                                parentId = 15,
                                replies = mutableListOf(
                                    Comment(
                                        id = nextId++,
                                        content = "E os componentes adaptam automaticamente! Surface, Card, tudo muda de forma consistente.",
                                        author = "MaterialFan",
                                        parentId = 16
                                    )
                                )
                            )
                        )
                    )
                )
            ),
            
            // ========== THREAD 3: Estado e ViewModel ==========
            Comment(
                id = nextId++,
                content = "Qual a melhor forma de gerenciar estado no Compose? State vs StateFlow vs LiveData?",
                author = "StateConfused",
                replies = mutableListOf(
                    Comment(
                        id = nextId++,
                        content = "StateFlow com ViewModel é o padrão recomendado pelo Google. Mais moderno e reativo.",
                        author = "ArchitectureExpert",
                        parentId = 18,
                        replies = mutableListOf(
                            Comment(
                                id = nextId++,
                                content = "Exato! StateFlow para lógica de negócio, State para UI local. Separação clara!",
                                author = "CleanArchitect",
                                parentId = 19,
                                replies = mutableListOf(
                                    Comment(
                                        id = nextId++,
                                        content = "E não esqueça do collectAsState() para observar o StateFlow na UI!",
                                        author = "FlowMaster",
                                        parentId = 20
                                    )
                                )
                            )
                        )
                    ),
                    Comment(
                        id = nextId++,
                        content = "Depende do caso. Para estado simples de UI, remember + mutableStateOf é suficiente.",
                        author = "PragmaticDev",
                        parentId = 18
                    )
                )
            )
        )
    }
}

