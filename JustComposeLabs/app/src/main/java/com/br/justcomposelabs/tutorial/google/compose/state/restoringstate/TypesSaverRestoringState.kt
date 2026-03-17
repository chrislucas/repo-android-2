package com.br.justcomposelabs.tutorial.google.compose.state.restoringstate

import androidx.compose.runtime.saveable.mapSaver

/*
    https://developer.android.com/develop/ui/compose/state#mapsaver
 */


data class User(val name: String, val email: String)


val UserSaver = run {
    val nameKey = "name"
    val emailKey = "email"

    mapSaver<User>(
        save = { user ->
            mapOf(nameKey to user.name, emailKey to user.email)
        },
        restore = { map ->
            User(
                name = map[nameKey] as? String ?: "",
                email = map[emailKey] as? String ?: ""
            )
        }
    )
}

/*
    ListSaver: https://developer.android.com/develop/ui/compose/state#listsaver
 */