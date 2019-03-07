package com.saledance.saledanceapp

data class PublishedPost(
    val business: Business,
    val post: Post,
    val publishTime: String,
    val sales: List<Sale>
)

