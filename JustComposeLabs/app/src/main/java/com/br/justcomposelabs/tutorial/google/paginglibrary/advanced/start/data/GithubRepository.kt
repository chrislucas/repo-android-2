package com.br.justcomposelabs.tutorial.google.paginglibrary.advanced.start.data


/*
    https://developer.android.com/codelabs/android-paging#1
    https://github.com/android/codelab-android-paging/blob/main/advanced/start/app/src/main/java/com/example/android/codelabs/paging/data/GithubRepository.kt
 */

// GitHub page API is 1 based: https://developer.github.com/v3/#pagination
private const val GITHUB_STARTING_PAGE_INDEX = 1

/**
 * Repository class that works with local and remote data sources.
 */
class GithubRepository() {



    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}