package com.br.justcomposelabs.tutorial.google.paginglibrary.basics.start.data

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/*
    https://developer.android.com/codelabs/android-paging-basics#2
    https://github.com/android/codelab-android-paging/blob/main/basic/start/app/src/main/java/com/example/android/codelabs/paging/data/ArticleRepository.kt
 */
data class Article(
    val id: Int,
    val title: String,
    val description: String,
    val created: LocalDateTime,
)


private val firstArticleCreatedTime = LocalDateTime.now()

/**
 * Repository class that mimics fetching [Article] instances from an asynchronous source.
 */
class ArticleRepository {
    /**
     * Exposed singular stream of [Article] instances
     */
    @RequiresApi(Build.VERSION_CODES.O)
    val articleStream: Flow<List<Article>> = flowOf(
        (0..500).map { number ->
            Article(
                id = number,
                title = "Article $number",
                description = "This describes article $number",
                created = firstArticleCreatedTime.minusDays(number.toLong())
            )
        }
    )
}

val Article.createdText: String @RequiresApi(Build.VERSION_CODES.O)
get() = articleDateFormatter.format(created)

@RequiresApi(Build.VERSION_CODES.O)
private val articleDateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")