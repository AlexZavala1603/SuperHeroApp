package com.example.superheroapp.model

import java.io.Serializable

/*
* @param id (int, optional): The unique ID of the character resource.
* @param name (string, optional): The name of the character.
* @param description (string, optional): A short bio or description of the character.
* @param modified (Date, optional): The date the resource was most recently modified.
* @param thumbnail (Image, optional): The representative image for this character.
*/

data class Character(
    var id:Int,
    var name:String,
    var description: String,
    var modified: String,
    var thumbnail: Thumbnail
): Serializable