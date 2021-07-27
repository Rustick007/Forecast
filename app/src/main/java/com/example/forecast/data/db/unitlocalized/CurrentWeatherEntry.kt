package com.example.forecast.data.db.unitlocalized

import androidx.room.ColumnInfo

class CurrentWeatherEntry(
        override val feelslike: Double,
        override val isDay: String,
        override val observationTime: String,
        override val precip: Double,
        override val pressure: Double,
        override val temperature: Double,
        override val visibility: Double,
        override val weatherCode: Int,
        override val windDir: String,
        override val windSpeed: Double) :UnitSpecificCurrentWeatherEntry {
}