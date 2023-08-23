package com.acabes.carapp.view_model

import retrofit2.Response
import retrofit2.http.GET

interface APIinterface {
    @GET("/v2/quotes/")
    suspend fun getQuote() : Response<List<String>>
}