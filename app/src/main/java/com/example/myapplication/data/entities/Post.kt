package com.example.myapplication.data.entities

import android.os.Parcel
import android.os.Parcelable

data class Post(
    val title: String,
    val numComments: Int,
    val thumbnail: String,
    val score: Int,
    val id :String,
    val author: String,
    var read: Boolean? = false
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readBoolean()
    )

    fun commentsText(): String = "$numComments comments"
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(numComments)
        parcel.writeString(thumbnail)
        parcel.writeInt(score)
        parcel.writeString(id)
        parcel.writeString(author)
        parcel.writeBoolean(read?: false)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}