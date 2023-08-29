package com.acabes.carapp.view_model

import com.acabes.carapp.model.CarDetails
import com.acabes.carapp.model.LoginRequest
import com.acabes.carapp.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIinterface {
    @GET("/v2/quotes/")
    suspend fun getQuote() : Response<List<String>>
    @GET("/api/vehicles/getallmanufacturers?format=json")
    suspend fun getCarDetails(): Response<CarDetails>
    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}
