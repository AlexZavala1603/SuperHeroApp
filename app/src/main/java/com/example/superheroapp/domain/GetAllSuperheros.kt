package com.example.superheroapp.domain

import com.example.superheroapp.data.repository.MarvelRepositoryImpl
import com.example.superheroapp.model.CharactersResponse
import retrofit2.Call

/* Use Case Get All Superheros. */
class GetAllSuperheros {
    private val repository = MarvelRepositoryImpl()

    suspend fun getSuperheros(offset: Int): Call<CharactersResponse?> = repository.getCharacters(offset)
}