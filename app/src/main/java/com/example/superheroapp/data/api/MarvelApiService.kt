package com.example.superheroapp.data.api

import com.example.superheroapp.data.helpers.RetrofitHelper
import com.example.superheroapp.model.CharactersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class MarvelApiService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCharacters(offset:Int): Call<CharactersResponse?> {
        return withContext(Dispatchers.IO){
            retrofit.create(MarvelApi::class.java).getCharacters(offset)
        }
    }
}