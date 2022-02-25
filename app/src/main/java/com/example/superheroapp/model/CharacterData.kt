package com.example.superheroapp.model

import com.google.gson.annotations.SerializedName

/*
* offset (int, optional): The requested offset (number of skipped results) of the call.
* total (int, optional): The total number of resources available given the current filter set.
* results (Array[Character], optional): The list of characters returned by the call.
*/

class CharacterData {
    @SerializedName("results")
    var characters: List<Character>? = null

    @SerializedName("total")
    var total: Int = 0

    @SerializedName("offset")
    var offset: Int = 0
}