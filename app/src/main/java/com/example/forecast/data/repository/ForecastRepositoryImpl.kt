package com.example.forecast.data.repository

import androidx.lifecycle.LiveData
import com.example.forecast.data.db.CurrentWeatherDao
import com.example.forecast.data.db.entity.CurrentWeatherEntry
import com.example.forecast.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import com.example.forecast.data.network.WeatherNetworkDataSource
import com.example.forecast.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
        private val currentweatherDao: CurrentWeatherDao,
        private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init{
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever{newCurrentWeather->
            //persist
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<CurrentWeatherEntry> {
        return  withContext(Dispatchers.IO){
            initWeatherData()
            return@withContext currentweatherDao.getWeatherMetric()
        }
    }



    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO) {
            currentweatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }
    private suspend fun initWeatherData(){
        if(isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()

    }
    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather(
              location = "Los Angeles",
                Locale.getDefault().language
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean{
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}