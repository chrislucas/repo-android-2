package com.experience.tutorial.todoapplist.withviewslivedata.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "tasks")
data class Task(
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "completed") val completed: Boolean = false,
    @PrimaryKey @ColumnInfo(name = "entryId") val id: String = "${UUID.randomUUID()}"
) {

    val titleForList: String
        get() = title.ifEmpty { description }

    val isActive
        get() = !completed

    val isEmpty
        get() = title.isNotEmpty() || description.isNotEmpty()
}