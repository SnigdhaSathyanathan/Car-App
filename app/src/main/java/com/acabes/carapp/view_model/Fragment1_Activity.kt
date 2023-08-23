package com.acabes.carapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acabes.carapp.model.Car
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class Fragment1_Activity : ViewModel(),CoroutineScope {
    private val job= Job()
    val quoteItem=MutableLiveData<String>()
    val carItem=MutableLiveData<List<Car>>()
    init {
        fetchQuote()
        fetchCarDetails()
    }
    fun fetchQuote(){
        launch {
            val api=RetrofitHelper().getInstance().create(APIinterface::class.java)
            quoteItem.value=api.getQuote().body()?.first()
        }
    }
    fun fetchCarDetails(){
        launch {
            val api=RetrofitCar().getInstance().create(APIcar::class.java)
            val array=api.getCarDetails().body()
            if (array != null) {
                carItem.value=array.Results
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job+ Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}