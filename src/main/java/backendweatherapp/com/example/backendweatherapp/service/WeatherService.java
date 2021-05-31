package backendweatherapp.com.example.backendweatherapp.service;

import backendweatherapp.com.example.backendweatherapp.model.OpenWeatherResponse;
import backendweatherapp.com.example.backendweatherapp.model.TopTenHottestCity;

import java.util.List;


public interface WeatherService {
    OpenWeatherResponse saveAvgTemperatureByLatLong(double lon, double lat, int cnt, String appid, String units, String cityName);

    List<TopTenHottestCity> getTopTenCities();

    void deleteFromTable();
}
