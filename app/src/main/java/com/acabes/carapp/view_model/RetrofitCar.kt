package com.acabes.carapp.view_model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCar {
    val baseUrl = "https://vpic.nhtsa.dot.gov/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}


