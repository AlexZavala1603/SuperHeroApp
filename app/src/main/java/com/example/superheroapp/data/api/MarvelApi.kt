package com.example.superheroapp.data.api

import com.example.superheroapp.model.CharactersResponse
import com.example.superheroapp.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("v1/public/characters")
    fun getCharacters(
        @Query("offset")
        offset: Int,
        @Query("apikey")
        apiKey: String = Constants.API_KEY,
        @Query("ts")
        ts: String = Constants.TS,
        @Query("hash")
        hash: String = Constants.HASH,
        @Query("limit")
        limit: Int = 100
    ): Call<CharactersResponse?>

}