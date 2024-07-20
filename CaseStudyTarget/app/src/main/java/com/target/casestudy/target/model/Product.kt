package com.example.android.caseStudy.model

data class Product(
    val aisle: String?,
    val availability: String?,
    val description: String,
    val fulfillment: String?,
    val id: Int,
    val image_url: String,
    val regular_price: Price?,
    val sale_price: Price?,
    val title: String
)