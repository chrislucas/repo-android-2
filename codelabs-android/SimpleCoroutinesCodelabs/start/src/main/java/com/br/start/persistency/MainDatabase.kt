package com.br.start.persistency

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class Title(val title: String, @PrimaryKey(autoGenerate = true) val id: Int = 0)


@Dao
interface TitleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(title: Title)

    @get:Query("SELECT * FROM Title WHERE id = 0")
    val titleLiveData: LiveData<Title?>


    @Query("SELECT * FROM Title WHERE id = :id")
    fun getTitle(id: Int)
}


@Database(entities = [Title::class], version = 1, exportSchema = false)
abstract class TitleDatabase : RoomDatabase() {
    abstract val titleDao: TitleDao
}


private lateinit var INSTANCE: TitleDatabase

private const val DBNAME = "title_db"

fun get(context: Context): RoomDatabase {
    synchronized(TitleDatabase::class) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                TitleDatabase::class.java,
                DBNAME
            ).fallbackToDestructiveMigration()
                .build()
        }
    }

    return INSTANCE
}