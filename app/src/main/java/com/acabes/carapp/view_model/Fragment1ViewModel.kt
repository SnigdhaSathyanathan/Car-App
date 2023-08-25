package com.acabes.carapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acabes.carapp.model.Car
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class Fragment1ViewModel : ViewModel(), CoroutineScope {
    private val job= Job()
    val quoteItem=MutableLiveData<String>()
    val carItem=MutableLiveData<List<Car>>()
    fun fetchQuote(){
        launch {
            val api=RetrofitHelper().getInstance().create(APIinterface::class.java)
            quoteItem.value=api.getQuote().body()?.first()
        }
    }
    fun fetchCarDetails(){
        launch {
            val api=RetrofitHelper().getCarInstance().create(APIinterface::class.java)
            carItem.value=api.getCarDetails().body()?.Results
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job+ Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}