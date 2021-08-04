package com.br.words.datalayer.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "words")
class WordEntity(
    @ColumnInfo(name = "word") val word: String
) {
    @PrimaryKey(autoGenerate = true)
    val key: Long = 0
}