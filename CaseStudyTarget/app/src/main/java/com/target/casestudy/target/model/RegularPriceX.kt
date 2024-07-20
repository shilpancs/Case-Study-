package com.example.android.caseStudy.model

import java.io.Serializable

data class RegularPriceX(
    val amount_in_cents: Int,
    val currency_symbol: String,
    val display_string: String
): Serializable