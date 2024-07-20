package com.target.casestudy.target

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.caseStudy.model.Product
import com.example.android.caseStudy.model.Products
import com.target.casestudy.target.network.DealsRespository
import com.target.casestudy.target.network.DealsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DealsViewModel @Inject constructor(
    private val dealsRepository: DealsRespository
): ViewModel() {
    private val _deals = MutableLiveData<Products>()

    val deals : LiveData<Products>
        get() = _deals

    private val _product_deal = MutableLiveData<Product>()

    val product_deal : LiveData<Product>
        get() = _product_deal

    fun fetchData() = viewModelScope.launch {
        _deals.value = dealsRepository.getDeals()
    }

    fun fetchProductDeal(id: Int) = viewModelScope.launch {
        _product_deal.value = dealsRepository.getProductDeal(id)
    }
}