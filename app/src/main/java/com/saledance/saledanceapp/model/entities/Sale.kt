package com.saledance.saledanceapp.model.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sale(
    val beforePrice: Double?,
    val id: Int?,
    val name: String?,
    val salePrice: Double?,
    val description: String?
): Parcelable