package com.br.datastore.tutorials.google.codelabs.preferencedatstore.data

import kotlinx.coroutines.flow.flowOf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TasksRepository {

    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    val tasks = flowOf(
        listOf(
            Task(
                name = "Task 1",
                deadline = simpleDateFormat.parse("2022-01-01")!!,
                priority = TaskPriority.High,
                isCompleted = true
            ),
            Task(
                name = "Task 2",
                deadline = simpleDateFormat.parse("2022-01-02")!!,
                priority = TaskPriority.Medium,
                isCompleted = false
            ),
            Task(
                name = "Open codelab",
                deadline = simpleDateFormat.parse("2020-07-03")!!,
                priority = TaskPriority.Low,
                isCompleted = true
            ),
            Task(
                name = "Import project",
                deadline = simpleDateFormat.parse("2020-04-03")!!,
                priority = TaskPriority.Medium,
                isCompleted = true
            ),
            Task(
                name = "Check out the code",
                deadline = simpleDateFormat.parse("2020-05-03")!!,
                priority = TaskPriority.Low
            ),
            Task(
                name = "Read about DataStore",
                deadline = simpleDateFormat.parse("2020-06-03")!!,
                priority = TaskPriority.High
            ),
            Task(
                name = "Implement each step",
                deadline = Date(),
                priority = TaskPriority.Medium
            ),
            Task(
                name = "Understand how to use DataStore",
                deadline = simpleDateFormat.parse("2020-04-03")!!,
                priority = TaskPriority.High
            ),
            Task(
                name = "Understand how to migrate to DataStore",
                deadline = Date(),
                priority = TaskPriority.High
            )
        )
    )
}