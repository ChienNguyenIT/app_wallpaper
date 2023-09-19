package com.khater.retromvvm.model.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {
    private const val BASE_URL = "https://wool-api.herokuapp.com/wallpapers/"
     private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: RetroService = retrofit.create(
        RetroService::class.java)

}