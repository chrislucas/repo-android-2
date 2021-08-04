package com.br.words.datalayer.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.br.words.datalayer.entities.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query(value = "SELECT * FROM words ORDER BY word ASC")
    fun getAscSortedWords() : List<WordEntity>

    /**
     * Observing Database changes
     * https://developer.android.com/codelabs/android-room-with-a-view-kotlin#6
     *
     *
     * */
    @Query(value = "SELECT * FROM words ORDER BY word ASC")
    fun getAscSortedFlowWords(): Flow<List<WordEntity>>

    /**
     * Estrategias de conflito ao inserir um valor na tabela.
     * Seguindo o codelabs, a estrategia sugerida foi a de ignorar
     * caso fosse encontrada a mesma palavra na base de dados. Vale
     * lembrar que exercicio original propunha criar uma tabela
     * de palavras, onde esse atributo tbm era a chave primaria na tabela,
     * o que deveria impedir a repeticao, mas ignoramos.
     *
     * Para fins de estudo, adicionei um atributo LONG com autoIncrement
     * para que ele seja a PK da tabela
     *
     * */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: WordEntity)

    @Query("DELETE From words")
    suspend fun deleteAll()
}