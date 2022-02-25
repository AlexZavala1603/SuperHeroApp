package com.example.superheroapp.data.repository

import com.example.superheroapp.data.api.MarvelApiService
import com.example.superheroapp.model.CharactersResponse
import retrofit2.Call

class MarvelRepositoryImpl: MarvelRepository {
    private var marvelApi : MarvelApiService = MarvelApiService()

    override suspend fun getCharacters(offset: Int): Call<CharactersResponse?> = marvelApi.getCharacters(offset)
}