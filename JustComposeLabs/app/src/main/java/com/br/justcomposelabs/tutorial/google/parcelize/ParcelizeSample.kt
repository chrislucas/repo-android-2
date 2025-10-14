package com.br.justcomposelabs.tutorial.google.parcelize

/*
    https://developer.android.com/kotlin/parcelize
 */

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
class User(val firstName: String, val lastName: String, val age: Int) : Parcelable {
    companion object : Parceler<User> {
        override fun User.write(
            parcel: Parcel,
            flags: Int
        ) {
            parcel.writeString(firstName)
            parcel.writeString(lastName)
            parcel.writeInt(age)
        }

        override fun create(parcel: Parcel): User {
            return User(
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readInt()
            )

        }
    }
}