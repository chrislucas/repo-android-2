package com.br.funwithjetpackcompose.tutorials.medium.androidsharedmemory

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.MemoryFile
import android.os.ParcelFileDescriptor
import android.os.SharedMemory
import androidx.annotation.RequiresApi
import java.io.FileDescriptor
import java.nio.ByteBuffer


/*
    TODO, estudar como prover uma SharedMemory Via Content provider


    Overview of memory management
    https://developer.android.com/topic/performance/memory-overview
 */


@RequiresApi(Build.VERSION_CODES.P)
class SharedMemoryContentProvider(
    private val nameOfSharedMemory: String,
    private val bufferSize: Int
) : ContentProvider() {

    private var sharedMemory: SharedMemory? = null


    override fun onCreate(): Boolean {
        synchronized(this) {
            if (sharedMemory == null) {
                sharedMemory = createSharedMemory(
                    nameOfSharedMemory = nameOfSharedMemory,
                    bufferSize = bufferSize
                )
            }
        }

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String?>?,
        selection: String?,
        selectionArgs: Array<out String?>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(
        uri: Uri,
        values: ContentValues?
    ): Uri? {
        return null
    }

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String?>?
    ): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String?>?
    ): Int {
        return 0
    }


    override fun openFile(uri: Uri, mode: String): ParcelFileDescriptor? {

        return null
    }


}