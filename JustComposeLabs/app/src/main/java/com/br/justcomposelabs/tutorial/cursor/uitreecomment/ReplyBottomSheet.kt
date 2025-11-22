package com.br.justcomposelabs.tutorial.cursor.uitreecomment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// BottomSheet para adicionar resposta ao comentário
@Composable
fun ReplyBottomSheetContent(
    parentComment: Comment,
    onDismiss: () -> Unit,
    onAddComment: (String, String) -> Unit
) {
    var author by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BottomSheetHeader(
            parentAuthor = parentComment.author,
            onDismiss = onDismiss
        )

        CommentContextCard(comment = parentComment)

        ReplyFormFields(
            author = author,
            onAuthorChange = { author = it },
            content = content,
            onContentChange = { content = it }
        )

        SendReplyButton(
            enabled = author.isNotBlank() && content.isNotBlank(),
            onClick = {
                if (author.isNotBlank() && content.isNotBlank()) {
                    onAddComment(author, content)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// Header da BottomSheet com título e botão fechar
@Composable
private fun BottomSheetHeader(
    parentAuthor: String,
    onDismiss: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Respondendo a ${parentAuthor.truncate(14)}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f, fill = false)
        )

        IconButton(onClick = onDismiss) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Fechar"
            )
        }
    }
}

// Card mostrando o comentário pai como contexto
@Composable
private fun CommentContextCard(comment: Comment) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = comment.author,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = comment.content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// Campos de formulário para resposta
@Composable
private fun ReplyFormFields(
    author: String,
    onAuthorChange: (String) -> Unit,
    content: String,
    onContentChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = author,
            onValueChange = onAuthorChange,
            label = { Text("Seu nome") },
            placeholder = { Text("Digite seu nome") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = content,
            onValueChange = onContentChange,
            label = { Text("Seu comentário") },
            placeholder = { Text("Digite sua resposta...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            maxLines = 6
        )
    }
}

// Botão de enviar resposta
@Composable
private fun SendReplyButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Send,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Enviar Resposta", style = MaterialTheme.typography.titleMedium)
    }
}

// ==================== PREVIEWS ====================

// Preview do BottomSheetHeader
@Preview(showBackground = true, widthDp = 360)
@Composable
fun PreviewBottomSheetHeader() {
    MaterialTheme {
        BottomSheetHeader(
            parentAuthor = "Maria Oliveira Santos",
            onDismiss = {}
        )
    }
}

// Preview do CommentContextCard
@Preview(showBackground = true, widthDp = 360)
@Composable
fun PreviewCommentContextCard() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            CommentContextCard(
                comment = Comment(
                    id = 1,
                    author = "João Silva",
                    content = "Este é o comentário pai ao qual você está respondendo. Pode conter bastante texto para demonstrar o layout."
                )
            )
        }
    }
}

// Preview do ReplyFormFields
@Preview(showBackground = true, widthDp = 360)
@Composable
fun PreviewReplyFormFields() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            ReplyFormFields(
                author = "Meu Nome",
                onAuthorChange = {},
                content = "Este é meu comentário de exemplo...",
                onContentChange = {}
            )
        }
    }
}

// Preview do SendReplyButton - Habilitado
@Preview(showBackground = true, widthDp = 360)
@Composable
fun PreviewSendReplyButtonEnabled() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            SendReplyButton(
                enabled = true,
                onClick = {}
            )
        }
    }
}

// Preview do SendReplyButton - Desabilitado
@Preview(showBackground = true, widthDp = 360)
@Composable
fun PreviewSendReplyButtonDisabled() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            SendReplyButton(
                enabled = false,
                onClick = {}
            )
        }
    }
}

// Preview do ReplyBottomSheetContent
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewReplyBottomSheetContent() {
    MaterialTheme {
        ReplyBottomSheetContent(
            parentComment = Comment(
                id = 1,
                author = "João Silva",
                content = "Este é um comentário muito interessante sobre desenvolvimento Android com Jetpack Compose."
            ),
            onDismiss = {},
            onAddComment = { _, _ -> }
        )
    }
}

