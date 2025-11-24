package com.br.funwithhilt.codelabs.usinghilt.data

import java.util.LinkedList
import javax.inject.Inject

class LoggerInMemoryDataSource @Inject constructor(): LoggerDataSource {

    private val logs = LinkedList<Log>()

    override fun addLog(message: String) {
    }

    override fun getAllLogs(callback: (List<Log>) -> Unit) {
    }

    override fun removeLogs() {
    }
}