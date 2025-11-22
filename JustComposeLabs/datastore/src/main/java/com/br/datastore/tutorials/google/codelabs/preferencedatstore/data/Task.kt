package com.br.datastore.tutorials.google.codelabs.preferencedatstore.data

import java.util.Date

sealed class TaskPriority {
    object Low : TaskPriority()
    object Medium : TaskPriority()
    object High : TaskPriority()
}

data class Task(
    val name: String,
    val deadline: Date,
    val priority: TaskPriority,
    val isCompleted: Boolean = false
)
