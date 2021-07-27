package com.example.forecast.data.db.unitlocalized

import com.google.gson.annotations.SerializedName

interface UnitSpecificCurrentWeatherEntry {
    val feelslike: Double
    val isDay: String
    val observationTime: String
    val precip: Double
    val pressure: Double
    val temperature: Double
    val visibility: Double
    val weatherCode: Int
    val windDir: String
    val windSpeed: Double
}