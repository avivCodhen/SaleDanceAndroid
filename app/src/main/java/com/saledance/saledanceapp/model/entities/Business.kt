package com.saledance.saledanceapp.model.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Business (
    val about: String?,
    val address: String?,
    val businessEmailContact: String?,
    val businessPhoneContact: String?,
    val friday: String?,
    val imageId: Int?,
    val name: String?,
    val saturday: String?,
    val site: String?,
    val weekDays: String?
) : Parcelable