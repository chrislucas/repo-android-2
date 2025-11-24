package com.br.funwithhilt.codelabs.usinghilt.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.br.funwithhilt.codelabs.usinghilt.data.LogDao

class LogsContentProvider: ContentProvider() {

    companion object {
        /** The authority of this content provider.  */
        private const val LOGS_TABLE = "logs"

        /** The authority of this content provider.  */
        private const val AUTHORITY = "com.example.android.hilt.provider"

        /** The match code for some items in the Logs table.  */
        private const val CODE_LOGS_DIR = 1

        /** The match code for an item in the Logs table.  */
        private const val CODE_LOGS_ITEM = 2
    }

    interface LogsContentProviderEntryPoint {
        fun logDao(): LogDao
    }

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String?>?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(
        uri: Uri,
        values: ContentValues?
    ): Uri? {
        TODO("Not yet implemented")
    }

    override fun onCreate(): Boolean = true

    override fun query(
        uri: Uri,
        projection: Array<out String?>?,
        selection: String?,
        selectionArgs: Array<out String?>?,
        sortOrder: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String?>?
    ): Int {
        TODO("Not yet implemented")
    }
}