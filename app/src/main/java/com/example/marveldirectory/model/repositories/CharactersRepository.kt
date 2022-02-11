package com.example.marveldirectory.model.repositories

import android.util.Log
import com.example.marveldirectory.model.data.MarvelResponse
import com.example.marveldirectory.model.network.MarvelClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest

class CharactersRepository {
    private val PUBLIC_KEY = "9c38f0f8a28c35cdc402a2704f1e95a4"
    private val PRIVATE_KEY = "c6ef2228ea9d4cde945fbf78a8493daa6bb1eeca"

    suspend fun getCharacters(ts:Int,limit:Int,offset:Int):MarvelResponse{
        return withContext(Dispatchers.IO){
            val response:Response<MarvelResponse> = MarvelClient.instance.getCharacters(ts,PUBLIC_KEY,md5("$ts"+PRIVATE_KEY+PUBLIC_KEY),limit, offset)
            Log.i("Response","$response")
            response.body() ?: MarvelResponse()
        }
    }

    fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}