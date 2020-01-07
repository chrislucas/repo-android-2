package com.xp.samplemvvmarch.model

import android.os.Parcel
import android.os.Parcelable

data class User(val id: Int, val name: String) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString() ?: "")

    override fun writeToParcel(writer: Parcel?, flags: Int) {
        writer?.apply {
            writeInt(id)
            writeString(name)
        }
    }

    override fun toString(): String = "name: $name, id: $id"

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}
