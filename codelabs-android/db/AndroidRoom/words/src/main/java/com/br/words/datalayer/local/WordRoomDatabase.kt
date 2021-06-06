package com.br.words.datalayer.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.br.words.datalayer.daos.WordDao
import com.br.words.datalayer.entities.WordEntity

/**
 * https://developer.android.com/codelabs/android-room-with-a-view-kotlin#7
 * What is Room database
 *  - Uma camada de sobre SQlite batabae
 *  - Toma conta de tarefas que seriam implementadas utilizando a classe QSliteOpenHelper
 *  - Utiliza interfaces DAO para realizar operacoes na base de dados
 *  - Para evitar que a UI tenha um desempenho ruim, Room nao permite realizar operacoes na base
 *  de dados na Main Thread. Quando as queries do Room retornam Flow elas sao automaticamente
 *  assincronas numa background thread
 *  - Room prove checagem de query em tempo de execucao
 *
 */

@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun getWordDaoInstance(): WordDao

    companion object {

        private const val DATABASE_NAME = "word_database"

        private var INSTANCE: WordRoomDatabase? = null

        fun getInstance(context: Context): WordRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}