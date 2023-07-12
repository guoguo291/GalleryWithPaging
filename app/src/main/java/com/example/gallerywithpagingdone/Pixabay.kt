package com.example.gallerywithpagingdone

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Pixabay (
    val totalHits:Int,
    val hits:Array<PhotoItem>,
    val total:Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pixabay

        if (totalHits != other.totalHits) return false
        if (!hits.contentEquals(other.hits)) return false
        if (total != other.total) return false

        return true
    }

    override fun hashCode(): Int {
        var result = totalHits
        result = 31 * result + hits.contentHashCode()
        result = 31 * result + total
        return result
    }
}


data class PhotoItem(
    @SerializedName("webformatURL") val previewUrl: String?,
    @SerializedName("id") val photoId:Int,
    @SerializedName("largeImageURL") val fullUrl: String?,
    @SerializedName("webformatHeight") val photoHeight:Int,
    @SerializedName("user") val photoUser: String?,
    @SerializedName("likes") val photoLikes:Int,
    @SerializedName("favorites") val photoFavorites:Int
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(previewUrl)
        parcel.writeInt(photoId)
        parcel.writeString(fullUrl)
        parcel.writeInt(photoHeight)
        parcel.writeString(photoUser)
        parcel.writeInt(photoLikes)
        parcel.writeInt(photoFavorites)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PhotoItem> {
        override fun createFromParcel(parcel: Parcel): PhotoItem {
            return PhotoItem(parcel)
        }

        override fun newArray(size: Int): Array<PhotoItem?> {
            return arrayOfNulls(size)
        }
    }
}