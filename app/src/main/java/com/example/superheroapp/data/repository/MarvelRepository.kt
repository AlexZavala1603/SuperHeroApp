package com.example.superheroapp.data.repository

import com.example.superheroapp.model.CharactersResponse
import retrofit2.Call

interface MarvelRepository {
    suspend fun getCharacters(offset:Int): Call<CharactersResponse?>
}