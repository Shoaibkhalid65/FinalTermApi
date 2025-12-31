package com.example.finaltermapi

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getProducts(): Response<List<User>>
}