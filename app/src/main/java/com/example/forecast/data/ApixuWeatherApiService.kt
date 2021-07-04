package com.example.forecast.data

import com.example.forecast.data.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_KEY="79bce3f5ba9f37e8544c846896791119"

//http://api.weatherstack.com/current?access_key=79bce3f5ba9f37e8544c846896791119&query=New%20York

interface ApixuWeatherApiService {

    @GET("current")
    fun getCurrentWeather(
            @Query("query") location: String,
            @Query("language") languageCode: String = "en"
    ): Deferred<CurrentWeatherResponse>

    companion object{
        operator fun invoke(): ApixuWeatherApiService{
            val requestInterceptor = Interceptor{ chain ->
              val url = chain.request()
                      .url()
                      .newBuilder()
                      .addQueryParameter("access_key", API_KEY)
                      .build()
              val request = chain.request()
                      .newBuilder()
                      .url(url)
                      .build()

              return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(requestInterceptor)
                    .build()

            return Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://api.weatherstack.com/")
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApixuWeatherApiService::class.java)
        }
    }
}