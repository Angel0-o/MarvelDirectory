package com.example.marveldirectory.model.network

import com.example.marveldirectory.model.data.DataResponse
import com.example.marveldirectory.model.data.MarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIMarvel {

    @GET("v1/public/characters")
    suspend fun getCharacters(@Query("ts") ts:Int,
                      @Query("apikey") apiKey:String,
                      @Query("hash") hash:String,
                      @Query("limit") limit:Int,
                      @Query("offset") offset:Int
    ):Response<MarvelResponse>
}