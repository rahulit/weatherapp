package backendweatherapp.com.example.backendweatherapp.service;

import backendweatherapp.com.example.backendweatherapp.model.OpenWeatherResponse;
import backendweatherapp.com.example.backendweatherapp.model.SevenDaysTemp;
import backendweatherapp.com.example.backendweatherapp.model.TopTenHottestCity;
import backendweatherapp.com.example.backendweatherapp.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@Service
public class WeatherServiceImpl implements WeatherService {

    private static final String URI = "http://api.openweathermap.org/data/2.5/onecall";

    private double avgTemp = 0.0;

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public OpenWeatherResponse saveAvgTemperatureByLatLong(double lon, double lat, int cnt, String appid, String units, String cityName) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(URI)
                .queryParam("lon", lon)
                .queryParam("lat", lat)
                .queryParam("appid", appid)
                .queryParam("units", units)
                .queryParam("exclude", "current,minutely,hourly,alerts");

        //Retrieve from OpenWeather API
        OpenWeatherResponse openWeatherResponse = restTemplate.getForObject(uriBuilder.toUriString(), OpenWeatherResponse.class);


        for (SevenDaysTemp sevenDaysTemp : openWeatherResponse.getDaily()) {
            avgTemp = avgTemp + Double.valueOf(sevenDaysTemp.getTemp().getDay());
        }

        final double avgTempOfCity = avgTemp / 7;

        weatherRepository.storeAvgTempOfCity(cityName, avgTempOfCity);

        avgTemp = 0.0;


        return openWeatherResponse;
    }

    @Override
    public List<TopTenHottestCity> getTopTenCities() {
        return weatherRepository.getTopTenCities();
    }

    @Override
    public void deleteFromTable() {
        weatherRepository.deleteFromTable();
    }
}
