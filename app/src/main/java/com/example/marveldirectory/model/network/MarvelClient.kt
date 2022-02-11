package com.example.marveldirectory.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MarvelClient {
    private const val BASE_URL = "https://gateway.marvel.com:443/"
    val instance : APIMarvel by lazy {
        val client = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIMarvel::class.java)
        client
    }
}