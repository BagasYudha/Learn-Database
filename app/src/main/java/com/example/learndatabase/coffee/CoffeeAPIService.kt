package com.example.learndatabase.coffee

import retrofit2.http.GET
import retrofit2.http.Path

interface CoffeeAPIService {
    @GET("hot")
    suspend fun getCoffee(): List<Coffee>
}
