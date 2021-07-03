package com.example.forecast.ui.weather.future.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forecast.R

class FutureDetailWeatherViewModel : Fragment() {

    companion object {
        fun newInstance() = FutureDetailWeatherViewModel()
    }

    private lateinit var viewModel: FutureDetailWeatherViewModelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.future_detail_weather_view_model_fragment,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FutureDetailWeatherViewModelViewModel::class.java)
        // TODO: Use the ViewModel
    }

}