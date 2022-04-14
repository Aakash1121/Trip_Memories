package com.example.tripmemories.model

import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter.writeString

data class TripData(
    var tripPhotosURI:String="",
    var tripTitle: String="",
    var tripDescription: String="",
    var tripDate: String="",
    var tripPeople: String="",

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(tripPhotosURI)
        writeString(tripTitle)
        writeString(tripDescription)
        writeString(tripDate)
        writeString(tripPeople)
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


