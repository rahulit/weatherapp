package backendweatherapp.com.example.backendweatherapp.repository;

import backendweatherapp.com.example.backendweatherapp.model.TopTenHottestCity;

import java.util.List;


public interface WeatherRepository {
    void storeAvgTempOfCity(String name, double avgTempOfCity);

    List<TopTenHottestCity> getTopTenCities();

    void deleteFromTable();
}
