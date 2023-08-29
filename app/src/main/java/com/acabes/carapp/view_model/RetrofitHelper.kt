package com.acabes.carapp.view_model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.acabes.carapp.constants.Constants

class RetrofitHelper {
    fun getInstance():Retrofit{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL_QUOTE).addConverterFactory(GsonConverterFactory.create()).build()

    }
    fun getCarInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL_CAR).addConverterFactory(GsonConverterFactory.create()).build()
    }
    fun getUserInstance():Retrofit{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL_AUTH).addConverterFactory(GsonConverterFactory.create()).build()
    }

}