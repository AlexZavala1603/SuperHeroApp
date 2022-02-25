package com.example.superheroapp.model

import com.google.gson.annotations.SerializedName

/*
* @param data (CharacterDataContainer, optional): The results returned by the call.
*/

class CharactersResponse {
    @SerializedName("data")
    var data: CharacterData? = null
}