package com.example.superheroapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertLongToTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy/MM/dd", Locale.US)
    return format.format(date)
}