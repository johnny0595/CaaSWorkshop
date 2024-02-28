package com.example.caasworkshop

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("images/search")
    suspend fun fetchRandomCatImage(): Response<List<CatImage>>
    data class CatImage(
        val id: String,
        val url: String
    )
}