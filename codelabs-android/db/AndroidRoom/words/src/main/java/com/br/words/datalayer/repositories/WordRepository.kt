package com.br.words.datalayer.repositories

import androidx.annotation.WorkerThread
import com.br.words.datalayer.daos.WordDao
import com.br.words.datalayer.entities.WordEntity
import kotlinx.coroutines.flow.Flow


class WordRepository(private val dao: WordDao) {


    val allWords: Flow<List<WordEntity>> = dao.getAscSortedFlowWords()

    /**
     * Por padrao o Room executa suspend queries (functions) fora da main thread
     * */

    // Denonta que o metodo anotado deve ser chamado por uma WorkerThread
    // se a classe recebe essa anotacao, todos os seus metodos devem ser chamados por uma worker thread

    // No sample tem uma @Suppress("RedundantSuspendModifier") provavelmente indicando a falta de necessidade
    // de usar o modificador suspend
    @WorkerThread
    suspend fun insert(wordEntity: WordEntity) {
        dao.insert(wordEntity)
    }

    @WorkerThread
    suspend fun deleteAll() {
        dao.deleteAll()
    }
}