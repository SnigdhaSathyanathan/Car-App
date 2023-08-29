package com.acabes.carapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acabes.carapp.model.Car
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {
    val quoteItem=MutableLiveData<String>()
    val carItem=MutableLiveData<List<Car>>()
    fun fetchQuote(){
        viewModelScope.launch {
            val api=RetrofitHelper().getInstance().create(APIinterface::class.java)
            quoteItem.value=api.getQuote().body()?.first()
        }
    }
    fun fetchCarDetails(){
        viewModelScope.launch {
            val api=RetrofitHelper().getCarInstance().create(APIinterface::class.java)
            carItem.value=api.getCarDetails().body()?.Results
        }
    }
}