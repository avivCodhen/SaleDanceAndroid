package com.saledance.saledanceapp.model.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class Sale(
    val beforePrice: Double,
    val image: String,
    val name: String,
    val salePrice: Double,
    val description: String
): Serializable