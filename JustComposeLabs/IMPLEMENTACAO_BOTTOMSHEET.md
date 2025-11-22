# Implementação: BottomSheet para Comentários

## 🎯 O Que Foi Solicitado

Substituir o Dialog por uma **BottomSheet** com as seguintes características:

1. ❌ **Remover** o Dialog antigo
2. ✅ Cada comentário deve ter um botão **"Comentário"** visível
3. ✅ Ao clicar, abre uma **BottomSheet** de baixo para cima
4. ✅ Título: **"Respondendo a {autor do comentário pai}"**
5. ✅ Mostrar o **comentário pai** como contexto
6. ✅ Campo de texto para **nome** e **comentário**
7. ✅ Enviar resposta e **atualizar automaticamente a UI**

## ✅ O Que Foi Implementado

### 1. **ReplyBottomSheetContent** (Novo)

```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReplyBottomSheetContent(
    parentComment: Comment,
    onDismiss: () -> Unit,
    onAddComment: (String, String) -> Unit
)
```

**Características:**
- 📱 **ModalBottomSheet** nativa do Material 3
- 🎯 Título dinâmico: "Respondendo a **{author}**"
- 📋 Card mostrando o comentário pai (contexto)
- ⌨️ Dois campos de texto:
  - **Seu nome** (obrigatório, linha única)
  - **Seu comentário** (obrigatório, 6 linhas)
- 🚀 Botão "Enviar Resposta" (desabilitado se campos vazios)
- ❌ Botão fechar no header
- 📐 **imePadding** para ajuste automático com teclado

### 2. **CommentItem Atualizado**

```kotlin
@Composable
fun CommentItem(
    comment: Comment,
    level: Int,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    onNavigate: (() -> Unit)? = null,
    onReply: () -> Unit  // <- Recebe o comentário completo agora
)
```

**Mudanças no Layout:**

#### Antes:
```
┌─────────────────────────────┐
│ Autor                       │
│ Conteúdo do comentário      │
│                             │
│              [+] [Ver →]    │
│ "2 respostas"               │
└─────────────────────────────┘
```

#### Depois:
```
┌─────────────────────────────┐
│ Autor (em negrito)          │
│ Conteúdo do comentário      │
│                             │
│ [+ Comentário]  "2 respostas" [Ver thread →] │
└─────────────────────────────┘
```

**Novo Botão "Comentário":**
- **Texto**: "Comentário" em negrito
- **Ícone**: ➕ antes do texto
- **Estilo**: TextButton com borda primária
- **Posição**: Primeira ação à esquerda
- **Destaque**: Cor primária do tema

### 3. **InfiniteScrollCommentTree Atualizado**

```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfiniteScrollCommentTree(
    comments: List<Comment>,
    modifier: Modifier = Modifier,
    onAddComment: (Comment, String, String) -> Unit
)
```

**Mudanças:**
- Estado agora guarda `Comment?` em vez de `Int?`
- Gerencia `ModalBottomSheetState`
- Usa `rememberCoroutineScope` para animações
- Auto-expande comentário ao navegar

### 4. **Fluxo de Adição de Comentário**

```mermaid
Usuário clica "Comentário"
    ↓
onReply(comment) é chamado
    ↓
showingReplyTo = comment
    ↓
ModalBottomSheet aparece
    ↓
Exibe: "Respondendo a {author}"
    ↓
Mostra comentário pai em card
    ↓
Usuário preenche nome e texto
    ↓
Clica "Enviar Resposta"
    ↓
onAddComment(parent, author, content)
    ↓
parent.addReply(newComment)
    ↓
comments = comments.toList() ← Força recomposição
    ↓
BottomSheet fecha
    ↓
UI atualiza automaticamente! ✅
```

## 🔄 Diferenças: Dialog vs BottomSheet

| Aspecto | Dialog (Antes) | BottomSheet (Agora) |
|---------|----------------|---------------------|
| **Posição** | Centro da tela | Desliza de baixo |
| **Animação** | Fade in/out | Slide up/down |
| **Fundo** | Escurece toda tela | Dim nativo |
| **Fechar** | Clicar fora ou botão | Arrastar, clicar fora ou botão |
| **Teclado** | Cobre o dialog | Ajusta automaticamente |
| **UX Mobile** | Menos natural | Mais natural |
| **Material 3** | ❌ | ✅ Nativo |
| **Drag Handle** | ❌ | ✅ Indicador visual |

## 🎨 Exemplo Visual da BottomSheet

### Estado Inicial
```
┌─────────────────────────────────┐
│ João                            │
│ Ótimo artigo sobre Compose!     │
│                                 │
│ [+ Comentário]  "3 respostas"   │
└─────────────────────────────────┘
        ↓ Usuário clica aqui
```

### BottomSheet Aberta
```
════════════════════════════════════
│  Respondendo a João          [X] │
├──────────────────────────────────┤
│  ┌────────────────────────────┐  │
│  │ João                       │  │
│  │ Ótimo artigo sobre Compose!│  │
│  └────────────────────────────┘  │
│                                   │
│  ┌────────────────────────────┐  │
│  │ Seu nome                   │  │
│  └────────────────────────────┘  │
│                                   │
│  ┌────────────────────────────┐  │
│  │ Seu comentário             │  │
│  │                            │  │
│  │                            │  │
│  │                            │  │
│  └────────────────────────────┘  │
│                                   │
│  ┌────────────────────────────┐  │
│  │  📤  Enviar Resposta       │  │
│  └────────────────────────────┘  │
════════════════════════════════════
```

### Após Enviar
```
┌─────────────────────────────────┐
│ João                            │
│ Ótimo artigo sobre Compose!     │
│                                 │
│ [+ Comentário]  "4 respostas"   │ ← Atualizado!
│   ├─────────────────────────┐   │
│   │ Maria                   │   │ ← Novo!
│   │ Concordo completamente! │   │
│   └─────────────────────────┘   │
└─────────────────────────────────┘
```

## 💻 Código Importante

### Uso no Preview

```kotlin
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CommentScreen() {
    var comments by remember { mutableStateOf(initialComments) }
    var nextId by remember { mutableStateOf(9) }
    
    val onAddComment: (Comment, String, String) -> Unit = { parent, author, content ->
        val newComment = Comment(
            id = nextId++,
            author = author,
            content = content,
            parentId = parent.id,
            replies = mutableListOf()
        )
        parent.addReply(newComment)
        // ⚠️ IMPORTANTE: Força recomposição
        comments = comments.toList()
    }
    
    MaterialTheme {
        InfiniteScrollCommentTree(
            comments = comments,
            onAddComment = onAddComment
        )
    }
}
```

### Integração da BottomSheet

```kotlin
// No InfiniteScrollCommentTree
if (showingReplyTo != null) {
    ModalBottomSheet(
        onDismissRequest = hideReplyBottomSheet,
        sheetState = sheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        ReplyBottomSheetContent(
            parentComment = showingReplyTo!!,
            onDismiss = hideReplyBottomSheet,
            onAddComment = { author, content ->
                onAddComment(showingReplyTo!!, author, content)
                hideReplyBottomSheet()
            }
        )
    }
}
```

## 📦 Imports Necessários

```kotlin
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.Close
```

## ✨ Melhorias de UX

### 1. **Validação em Tempo Real**
- Botão "Enviar" desabilitado se campos vazios
- Feedback visual instantâneo

### 2. **Contexto Sempre Visível**
- Comentário pai mostrado em card
- Usuário sempre sabe a quem está respondendo

### 3. **Animações Suaves**
- BottomSheet desliza naturalmente
- Transições do Material 3

### 4. **Ajuste com Teclado**
- `imePadding` garante que campos não fiquem escondidos
- Layout ajusta automaticamente

### 5. **Múltiplas Formas de Fechar**
- Botão X no header
- Arrastar para baixo
- Clicar no fundo escurecido

## 🐛 Pontos de Atenção

### ⚠️ Importante para Atualização da UI

```kotlin
// ❌ ERRADO - Não força recomposição
parent.addReply(newComment)

// ✅ CORRETO - Força recomposição
parent.addReply(newComment)
comments = comments.toList()  // Cria nova referência
```

### 🔒 Estado do Sheet

```kotlin
val sheetState = rememberModalBottomSheetState(
    skipPartiallyExpanded = true  // Abre completamente
)
```

### 🎯 Callback com Objeto Completo

```kotlin
// ✅ Passa o comentário completo, não só o ID
onShowReply: (Comment) -> Unit
// Não: onShowReply: (Int) -> Unit
```

## 📱 Comportamento Mobile

### Gestos Suportados:
- **Arrastar para baixo**: Fecha a BottomSheet
- **Toque fora**: Fecha a BottomSheet
- **Botão X**: Fecha programaticamente
- **Swipe rápido**: Animação de dismiss

### Acessibilidade:
- Labels em todos os campos
- ContentDescription em ícones
- Cores de contraste adequadas
- Tamanhos de toque adequados (48dp mínimo)

## 🎯 Resultado Final

✅ **Dialog removido completamente**  
✅ **BottomSheet nativa implementada**  
✅ **Botão "Comentário" em destaque**  
✅ **Título contextual dinâmico**  
✅ **Comentário pai visível**  
✅ **Atualização automática da UI**  
✅ **Validação de campos**  
✅ **Ajuste com teclado**  
✅ **Animações suaves**  
✅ **UX mobile otimizada**  

---

**✨ Implementação completa e funcional!**


