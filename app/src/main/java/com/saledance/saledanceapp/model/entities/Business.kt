package com.saledance.saledanceapp.model.entities

import java.io.Serializable

data class Business (
    val about: String,
    val address: String,
    val businessEmailContact: String,
    val businessPhoneContact: String,
    val friday: String,
    val image: String,
    val name: String,
    val saturday: String,
    val site: String,
    val weekDays: String
) : Serializable