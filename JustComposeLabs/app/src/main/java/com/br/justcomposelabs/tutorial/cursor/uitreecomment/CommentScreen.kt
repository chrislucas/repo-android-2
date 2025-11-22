package com.br.justcomposelabs.tutorial.cursor.uitreecomment

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CommentScreen() {
    val viewModel: CommentViewModel = viewModel()
    val comments by viewModel.comments.collectAsState()

    MaterialTheme {
        InfiniteScrollCommentTree(
            comments = comments,
            onAddComment = { parent, author, content ->
                viewModel.addComment(parent, author, content)
            }
        )
    }
}

