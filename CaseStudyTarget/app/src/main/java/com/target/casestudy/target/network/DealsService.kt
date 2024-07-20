package com.target.casestudy.target.network

import com.example.android.caseStudy.model.Product
import com.example.android.caseStudy.model.Products
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DealsService {

    @GET("deals")
    suspend fun getDeals(): Products

    @GET("deals/{id}")
    suspend fun getProductDeal(@Path("id") productId: Int): Product
}