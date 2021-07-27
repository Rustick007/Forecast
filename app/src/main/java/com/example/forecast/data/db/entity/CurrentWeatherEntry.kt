package com.example.forecast.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_ weather")
data class CurrentWeatherEntry(
    val feelslike: Double,
    @SerializedName("is_day")
    val isDay: String,
    @SerializedName("observation_time")
    val observati5onTime: String,
    val precip: Double,
    val pressure: Double,
    val temperature: Double,
    val visibility: Double,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_speed")
    val windSpeed: Double
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID
}