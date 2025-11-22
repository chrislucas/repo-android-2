package com.br.justcomposelabs.tutorial.cursor.uitreecomment

// Função auxiliar para truncar texto sem adicionar reticências
fun String.truncate(maxLength: Int): String {
    return if (length > maxLength) "${take(maxLength)} ..." else this
}

// Data class representando um comentário
data class Comment(
    val id: Int,
    val content: String,
    val author: String,
    val parentId: Int? = null,
    val replies: MutableList<Comment> = mutableListOf()
) {
    // Função auxiliar para adicionar uma resposta
    fun addReply(reply: Comment): Comment {
        replies.add(reply)
        return this
    }

    // Função auxiliar para encontrar um comentário por ID na árvore
    fun findComment(commentId: Int): Comment? {
        if (this.id == commentId) return this
        replies.forEach { reply ->
            val found = reply.findComment(commentId)
            if (found != null) return found
        }
        return null
    }
}

// Função auxiliar para encontrar um comentário na lista
fun findCommentInList(comments: List<Comment>, commentId: Int): Comment? {
    comments.forEach { comment ->
        val found = comment.findComment(commentId)
        if (found != null) return found
    }
    return null
}

