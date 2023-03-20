package com.example.androidblockstoreapi.helper

import android.content.Context

import com.google.android.gms.auth.blockstore.Blockstore
import com.google.android.gms.auth.blockstore.BlockstoreClient
import com.google.android.gms.auth.blockstore.RetrieveBytesRequest
import com.google.android.gms.auth.blockstore.StoreBytesData
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener

/*
    https://www.youtube.com/watch?v=WcU7r53xObg
 */
fun BlockStoreListener.saveInBlockStore(ctx: Context) {

    val (key, value) = data

    val storeRequest: StoreBytesData = StoreBytesData.Builder()
        .setBytes(value)
        .setKey(key)
        .build()

    Blockstore.getClient(ctx)
        .storeBytes(storeRequest)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}


fun retrieveFromBlockStore(keys: List<String>, context: Context) {
    Blockstore.getClient(context)
    RetrieveBytesRequest.Builder()
        .setKeys(keys)
        .build()

}

data class BlockStoreListener(
    val data: Pair<String, ByteArray>,
    val onSuccessListener: OnSuccessListener<Int>,
    val onFailureListener: OnFailureListener
)