package com.target.casestudy.target.network

import com.example.android.caseStudy.model.Product
import com.example.android.caseStudy.model.Products
import retrofit2.Response
import javax.inject.Inject

class DealsRespository @Inject constructor(
    private val dealsService: DealsService
) {

    suspend fun getDeals(): Products {
        return dealsService.getDeals()
    }

    suspend fun getProductDeal(id: Int): Product {
        return dealsService.getProductDeal(id)
    }
}