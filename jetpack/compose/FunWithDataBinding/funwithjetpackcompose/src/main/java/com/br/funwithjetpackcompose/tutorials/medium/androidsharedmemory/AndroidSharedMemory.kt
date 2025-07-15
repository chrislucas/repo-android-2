package com.br.funwithjetpackcompose.tutorials.medium.androidsharedmemory

import android.os.Build
import android.os.SharedMemory
import androidx.annotation.RequiresApi
import java.nio.ByteBuffer

/*
    https://buraaktasci.medium.com/making-any-class-lifecycle-aware-in-android-a-practical-guide-db9d1ce68a93
    https://developer.android.com/reference/android/os/SharedMemory

    IPC = inter-process communication


    https://www.linkedin.com/pulse/shared-memory-fastest-way-share-data-between-android-stefano-santilli/
 */


@RequiresApi(Build.VERSION_CODES.O_MR1)
fun createSharedMemory(bufferSize: Int, nameOfSharedMemory: String) =
    SharedMemory.create(nameOfSharedMemory, bufferSize)


/*
    Só para lembrar que da para usar o 'use' no SharedMemory
 */
@RequiresApi(Build.VERSION_CODES.O_MR1)
fun <R> SharedMemory.test(fn: (SharedMemory) -> R) = use(fn)

fun ByteBuffer.writeString(data: String) = put(data.toByteArray(Charsets.UTF_8))