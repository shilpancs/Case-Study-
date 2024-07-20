package com.example.android.caseStudy.model

import java.io.Serializable

data class ProductDetails(
    val aisle: String,
    val availability: String,
    val description: String,
    val fulfillment: String,
    val id: Int,
    val image_url: String,
    val regular_price: RegularPriceX,
    val title: String
): Serializable