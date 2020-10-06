package com.example.news
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemOfList(
    val photo: String?,
    val imageTitle: String?,
    val imageDescription:String?,
    val imageContext: String?,
    val imageAuthor:String?,
    val imageYear:String?
): Parcelable



