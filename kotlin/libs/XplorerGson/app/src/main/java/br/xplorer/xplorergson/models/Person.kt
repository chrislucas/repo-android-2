package br.xplorer.xplorergson.models

import com.google.gson.annotations.SerializedName

data class Person(@SerializedName("_id") val id: Long, @SerializedName("_name") val name: String)