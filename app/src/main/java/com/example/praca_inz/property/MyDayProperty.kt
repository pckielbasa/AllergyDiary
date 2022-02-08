package com.example.praca_inz.property

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyDayProperty(
    val _id:String,
    val hour: String,
    val minute: String
): Parcelable {

}