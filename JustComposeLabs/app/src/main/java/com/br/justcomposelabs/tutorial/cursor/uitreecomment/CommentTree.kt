package com.br.justcomposelabs.tutorial.cursor.uitreecomment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

// Composable principal que exibe a árvore de comentários com scroll infinito
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfiniteScrollCommentTree(
    comments: List<Comment>,
    modifier: Modifier = Modifier,
    onAddComment: (Comment, String, String) -> Unit = { _, _, _ -> }
) {
    var expandedComments by remember { mutableStateOf(setOf<Int>()) }
    var focusedCommentId by remember { mutableStateOf<Int?>(null) }
    var showingReplyTo by remember { mutableStateOf<Comment?>(null) }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    val toggleComment: (Comment) -> Unit = { comment ->
        expandedComments = if (expandedComments.contains(comment.id)) {
            expandedComments - comment.id
        } else {
            expandedComments + comment.id
        }
    }

    val navigateToComment: (Int) -> Unit = { commentId ->
        focusedCommentId = commentId
        // Auto-expande o comentário focado
        expandedComments = expandedComments + commentId
    }

    val backToRoot: () -> Unit = {
        focusedCommentId = null
    }

    val showReplyBottomSheet: (Comment) -> Unit = { comment ->
        showingReplyTo = comment
    }

    val hideReplyBottomSheet: () -> Unit = {
        scope.launch {
            sheetState.hide()
            showingReplyTo = null
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {
        // Barra de navegação
        if (focusedCommentId != null) {
            NavigationBar(onBack = backToRoot)
        }

        // Lista de comentários com scroll
        ScrollableCommentList(
            comments = comments,
            focusedCommentId = focusedCommentId,
            expandedComments = expandedComments,
            onToggleComment = toggleComment,
            onNavigateToComment = navigateToComment,
            onShowReply = showReplyBottomSheet,
            modifier = Modifier.weight(1f)
        )

        // BottomSheet para adicionar comentário
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
    }
}

// Barra de navegação para voltar à raiz
@Composable
fun NavigationBar(onBack: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Text(
                text = "Navegando em comentário",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

// Lista scrollável de comentários
@Composable
fun ScrollableCommentList(
    comments: List<Comment>,
    focusedCommentId: Int?,
    expandedComments: Set<Int>,
    onToggleComment: (Comment) -> Unit,
    onNavigateToComment: (Int) -> Unit,
    onShowReply: (Comment) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    // Se há um comentário focado, mostra apenas ele e seu contexto
    val displayComments = remember(comments, focusedCommentId) {
        if (focusedCommentId != null) {
            val focusedComment = findCommentInList(comments, focusedCommentId)
            if (focusedComment != null) listOf(focusedComment) else comments
        } else {
            comments
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxWidth()
    ) {
        displayComments.forEach { comment ->
            renderCommentTree(
                comment = comment,
                level = 0,
                expandedComments = expandedComments,
                onToggleComment = onToggleComment,
                onNavigateToComment = onNavigateToComment,
                onShowReply = onShowReply
            )
        }
    }
}

// Função de extensão para LazyListScope renderizar a árvore recursivamente
// Limita a 3 níveis (0, 1, 2) para evitar texto espremido
fun LazyListScope.renderCommentTree(
    comment: Comment,
    level: Int,
    expandedComments: Set<Int>,
    onToggleComment: (Comment) -> Unit,
    onNavigateToComment: (Int) -> Unit,
    onShowReply: (Comment) -> Unit,
    maxLevel: Int = 2 // Máximo 3 níveis (0, 1, 2)
) {
    val isExpanded = expandedComments.contains(comment.id)
    val hasReplies = comment.replies.isNotEmpty()
    val reachedMaxLevel = level >= maxLevel

    item(key = comment.id) {
        CommentItem(
            comment = comment,
            level = level,
            isExpanded = isExpanded,
            onToggle = { onToggleComment(comment) },
            onNavigate = if (hasReplies) {
                { onNavigateToComment(comment.id) }
            } else null,
            onReply = { onShowReply(comment) },
            reachedMaxLevel = reachedMaxLevel && hasReplies
        )
    }

    // Se expandido e não atingiu o nível máximo, renderiza as respostas recursivamente
    // Caso contrário, usuário deve usar navegação focada
    if (isExpanded && hasReplies && !reachedMaxLevel) {
        comment.replies.forEach { reply ->
            renderCommentTree(
                comment = reply,
                level = level + 1,
                expandedComments = expandedComments,
                onToggleComment = onToggleComment,
                onNavigateToComment = onNavigateToComment,
                onShowReply = onShowReply,
                maxLevel = maxLevel
            )
        }
    }
}

// ==================== PREVIEWS ====================

// Preview do NavigationBar
@Preview(showBackground = true, widthDp = 360)
@Composable
fun PreviewNavigationBar() {
    MaterialTheme {
        NavigationBar(onBack = {})
    }
}

// Preview do InfiniteScrollCommentTree - Versão compacta
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewInfiniteScrollCommentTree() {
    val sampleComments = listOf(
        Comment(
            id = 1,
            author = "Usuário 1",
            content = "Comentário de nível 1",
            replies = mutableListOf(
                Comment(
                    id = 2,
                    author = "Usuário 2",
                    content = "Resposta de nível 2",
                    parentId = 1,
                    replies = mutableListOf(
                        Comment(
                            id = 3,
                            author = "Usuário 3",
                            content = "Resposta de nível 3",
                            parentId = 2
                        )
                    )
                )
            )
        ),
        Comment(
            id = 4,
            author = "Usuário 4",
            content = "Outro comentário",
            replies = mutableListOf()
        )
    )

    MaterialTheme {
        InfiniteScrollCommentTree(
            comments = sampleComments,
            onAddComment = { _, _, _ -> }
        )
    }
}

