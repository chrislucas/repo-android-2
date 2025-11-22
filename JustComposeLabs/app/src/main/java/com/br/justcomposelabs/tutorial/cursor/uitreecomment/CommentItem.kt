package com.br.justcomposelabs.tutorial.cursor.uitreecomment

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CommentItem(
    comment: Comment,
    level: Int,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    onNavigate: (() -> Unit)? = null,
    onReply: () -> Unit,
    reachedMaxLevel: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = (level * 16).dp, top = 4.dp, bottom = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommentExpandIcon(
                hasReplies = comment.replies.isNotEmpty(),
                isExpanded = isExpanded,
                reachedMaxLevel = reachedMaxLevel,
                onToggle = onToggle,
                onNavigate = onNavigate
            )

            CommentCard(
                comment = comment,
                level = level,
                reachedMaxLevel = reachedMaxLevel,
                onReply = onReply,
                onNavigate = onNavigate
            )
        }
    }
}

// Ícone de expandir/colapsar ou navegação
@Composable
private fun CommentExpandIcon(
    hasReplies: Boolean,
    isExpanded: Boolean,
    reachedMaxLevel: Boolean,
    onToggle: () -> Unit,
    onNavigate: (() -> Unit)?
) {
    when {
        hasReplies && reachedMaxLevel -> {
            IconButton(onClick = onNavigate ?: {}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Ver respostas",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }

        hasReplies -> {
            val iconVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore
            val iconDescription = if (isExpanded) "Colapsar" else "Expandir"
            
            IconButton(onClick = onToggle) {
                Icon(
                    imageVector = iconVector,
                    contentDescription = iconDescription
                )
            }
        }

        else -> {
            Spacer(modifier = Modifier.width(48.dp))
        }
    }
}

// Card principal do comentário
@Composable
private fun CommentCard(
    comment: Comment,
    level: Int,
    reachedMaxLevel: Boolean,
    onReply: () -> Unit,
    onNavigate: (() -> Unit)?
) {
    val cardColor = if (level == 0) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surface
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            CommentHeader(
                author = comment.author,
                content = comment.content
            )

            Spacer(modifier = Modifier.height(12.dp))

            CommentActions(
                comment = comment,
                reachedMaxLevel = reachedMaxLevel,
                onReply = onReply,
                onNavigate = onNavigate
            )

            if (reachedMaxLevel) {
                MaxLevelWarning()
            }
        }
    }
}

// Cabeçalho do comentário (autor e conteúdo)
@Composable
private fun CommentHeader(
    author: String,
    content: String
) {
    Column {
        Text(
            text = author,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

// Linha de ações do comentário
@Composable
private fun CommentActions(
    comment: Comment,
    reachedMaxLevel: Boolean,
    onReply: () -> Unit,
    onNavigate: (() -> Unit)?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ReplyButton(onClick = onReply)

        CommentMetadata(
            comment = comment,
            reachedMaxLevel = reachedMaxLevel,
            onNavigate = onNavigate
        )
    }
}

// Botão de responder
@Composable
private fun ReplyButton(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .border(
                1.dp,
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(6.dp)
            ),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp) // Reduz padding interno
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Comentário",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(end = 2.dp)
                .size(16.dp) // Reduz tamanho do ícone
        )
        Text(
            "Comentar",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelMedium // Fonte menor
        )
    }
}

// Metadados do comentário (contador e navegação)
@Composable
private fun CommentMetadata(
    comment: Comment,
    reachedMaxLevel: Boolean,
    onNavigate: (() -> Unit)?
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (comment.replies.isNotEmpty()) {
            ReplyCount(count = comment.replies.size)

            if (onNavigate != null) {
                NavigateButton(
                    reachedMaxLevel = reachedMaxLevel,
                    onClick = onNavigate
                )
            }
        }
    }
}

// Contador de respostas
@Composable
private fun ReplyCount(count: Int) {
    val replyText = if (count == 1) "resposta" else "respostas"
    
    Text(
        text = "$count $replyText",
        style = MaterialTheme.typography.labelSmall,
        color = Color.Gray
    )
}

// Botão de navegação para thread
@Composable
private fun NavigateButton(
    reachedMaxLevel: Boolean,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {

        val content = if (reachedMaxLevel) "Respostas →" else "Thread →"
        val textColor = if (reachedMaxLevel) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurface
        }
        val textFontWeight = if (reachedMaxLevel) FontWeight.Bold else FontWeight.Normal
        
        Text(
            text = content,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = textFontWeight,
            color = textColor
        )
    }
}

// Aviso quando atingir nível máximo
@Composable
private fun MaxLevelWarning() {
    Spacer(modifier = Modifier.height(8.dp))
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                "Clique em 'Ver respostas' para continuar a thread",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// ==================== PREVIEWS ====================

// Preview do CommentItem - Estado normal
@Preview(showBackground = true)
@Composable
fun PreviewCommentItem() {
    MaterialTheme {
        CommentItem(
            comment = Comment(
                id = 1,
                author = "João Silva",
                content = "Este é um comentário de exemplo com texto suficiente para testar o layout e verificar como fica a exibição.",
                replies = mutableListOf(
                    Comment(id = 2, author = "Maria", content = "Resposta 1"),
                    Comment(id = 3, author = "Pedro", content = "Resposta 2")
                )
            ),
            level = 0,
            isExpanded = false,
            onToggle = {},
            onNavigate = {},
            onReply = {},
            reachedMaxLevel = false
        )
    }
}

// Preview do CommentItem - Expandido
@Preview(showBackground = true)
@Composable
fun PreviewCommentItemExpanded() {
    MaterialTheme {
        CommentItem(
            comment = Comment(
                id = 1,
                author = "João Silva",
                content = "Comentário expandido",
                replies = mutableListOf(
                    Comment(id = 2, author = "Maria", content = "Resposta")
                )
            ),
            level = 0,
            isExpanded = true,
            onToggle = {},
            onNavigate = {},
            onReply = {},
            reachedMaxLevel = false
        )
    }
}

// Preview do CommentItem - Nível máximo atingido
@Preview(showBackground = true)
@Composable
fun PreviewCommentItemMaxLevel() {
    MaterialTheme {
        CommentItem(
            comment = Comment(
                id = 1,
                author = "João Silva",
                content = "Comentário no nível máximo. Deve mostrar aviso de navegação.",
                replies = mutableListOf(
                    Comment(id = 2, author = "Maria", content = "Resposta oculta")
                )
            ),
            level = 2,
            isExpanded = false,
            onToggle = {},
            onNavigate = {},
            onReply = {},
            reachedMaxLevel = true
        )
    }
}

// Preview do CommentItem - Sem respostas
@Preview(showBackground = true)
@Composable
fun PreviewCommentItemNoReplies() {
    MaterialTheme {
        CommentItem(
            comment = Comment(
                id = 1,
                author = "Ana Costa",
                content = "Comentário sem respostas",
                replies = mutableListOf()
            ),
            level = 1,
            isExpanded = false,
            onToggle = {},
            onNavigate = null,
            onReply = {}
        )
    }
}

// Preview do CommentExpandIcon - Com respostas não expandido
@Preview(showBackground = true)
@Composable
fun PreviewCommentExpandIcon() {
    MaterialTheme {
        Row(modifier = Modifier.padding(16.dp)) {
            CommentExpandIcon(
                hasReplies = true,
                isExpanded = false,
                reachedMaxLevel = false,
                onToggle = {},
                onNavigate = {}
            )
        }
    }
}

// Preview do CommentExpandIcon - Nível máximo
@Preview(showBackground = true)
@Composable
fun PreviewCommentExpandIconMaxLevel() {
    MaterialTheme {
        Row(modifier = Modifier.padding(16.dp)) {
            CommentExpandIcon(
                hasReplies = true,
                isExpanded = false,
                reachedMaxLevel = true,
                onToggle = {},
                onNavigate = {}
            )
        }
    }
}

// Preview do CommentHeader
@Preview(showBackground = true)
@Composable
fun PreviewCommentHeader() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            CommentHeader(
                author = "Maria Oliveira",
                content = "Este é o conteúdo do comentário que pode ser bem longo e ocupar várias linhas para demonstrar como o layout se comporta."
            )
        }
    }
}

// Preview do ReplyButton
@Preview(showBackground = true)
@Composable
fun PreviewReplyButton() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            ReplyButton(onClick = {})
        }
    }
}

// Preview do ReplyCount
@Preview(showBackground = true)
@Composable
fun PreviewReplyCount() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ReplyCount(count = 1)
            ReplyCount(count = 5)
            ReplyCount(count = 42)
        }
    }
}

// Preview do NavigateButton - Normal
@Preview(showBackground = true)
@Composable
fun PreviewNavigateButton() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NavigateButton(
                reachedMaxLevel = false,
                onClick = {}
            )
        }
    }
}

// Preview do NavigateButton - Nível máximo
@Preview(showBackground = true)
@Composable
fun PreviewNavigateButtonMaxLevel() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NavigateButton(
                reachedMaxLevel = true,
                onClick = {}
            )
        }
    }
}

// Preview do MaxLevelWarning
@Preview(showBackground = true, widthDp = 320)
@Composable
fun PreviewMaxLevelWarning() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            MaxLevelWarning()
        }
    }
}

// Preview do CommentActions
@Preview(showBackground = true, widthDp = 360)
@Composable
fun PreviewCommentActions() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            CommentActions(
                comment = Comment(
                    id = 1,
                    author = "João",
                    content = "Comentário",
                    replies = mutableListOf(
                        Comment(id = 2, author = "Maria", content = "R1"),
                        Comment(id = 3, author = "Pedro", content = "R2")
                    )
                ),
                reachedMaxLevel = false,
                onReply = {},
                onNavigate = {}
            )
        }
    }
}

// Preview do CommentMetadata
@Preview(showBackground = true)
@Composable
fun PreviewCommentMetadata() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            CommentMetadata(
                comment = Comment(
                    id = 1,
                    author = "João",
                    content = "Teste",
                    replies = mutableListOf(
                        Comment(id = 2, author = "Maria", content = "R1"),
                        Comment(id = 3, author = "Pedro", content = "R2"),
                        Comment(id = 4, author = "Ana", content = "R3")
                    )
                ),
                reachedMaxLevel = false,
                onNavigate = {}
            )
        }
    }
}

