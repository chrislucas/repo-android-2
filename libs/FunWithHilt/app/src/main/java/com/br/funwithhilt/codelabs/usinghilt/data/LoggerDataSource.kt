package com.br.funwithhilt.codelabs.usinghilt.data

interface LoggerDataSource {
    fun addLog(message: String)
    fun getAllLogs(callback: (List<Log>) -> Unit)
    fun removeLogs()
}