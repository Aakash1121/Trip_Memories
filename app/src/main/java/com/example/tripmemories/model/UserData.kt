package com.example.tripmemories.model

import android.os.Parcel
import android.os.Parcelable


data class UserData(
    var id: String = "",
    var firstName: String= "",
    var lastName: String= "",
    var email: String= "",

    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(id)
        writeString(firstName)
        writeString(lastName)
        writeString(email)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<UserData> {
        override fun createFromParcel(parcel: Parcel): UserData {
            return UserData(parcel)
        }

        override fun newArray(size: Int): Array<UserData?> {
            return arrayOfNulls(size)
        }
    }
}
