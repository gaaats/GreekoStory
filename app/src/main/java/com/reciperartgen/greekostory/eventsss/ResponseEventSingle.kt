package com.reciperartgen.greekostory.eventsss


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseEventSingle(
    @SerializedName("day")
    val day: String?,
    @SerializedName("event")
    val event: String?,
    @SerializedName("month")
    val month: String?,
    @SerializedName("year")
    val year: String?
) : Parcelable