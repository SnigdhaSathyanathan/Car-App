package com.acabes.carapp.view_model

import com.acabes.carapp.model.CarDetails
import retrofit2.Response
import retrofit2.http.GET

interface APIinterface {
    @GET("/v2/quotes/")
    suspend fun getQuote() : Response<List<String>>
    @GET("/api/vehicles/getallmanufacturers?format=json")
    suspend fun getCarDetails(): Response<CarDetails>
}