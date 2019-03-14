package com.saledance.saledanceapp.model.entities

data class PublishedPost(
    val business: Business,
    val post: Post,
    val publishTime: String,
    val sales: List<Sale>
)

