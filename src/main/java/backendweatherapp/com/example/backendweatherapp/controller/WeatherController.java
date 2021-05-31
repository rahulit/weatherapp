package backendweatherapp.com.example.backendweatherapp.controller;


import backendweatherapp.com.example.backendweatherapp.model.TopTenHottestCity;
import backendweatherapp.com.example.backendweatherapp.model.USStateCapital;
import backendweatherapp.com.example.backendweatherapp.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    ResourceLoader resourceLoader;
    private List<USStateCapital> usStateCapitalList = null;

    /*  Class getTopSevenHottestCities() to retrieve 7 day temperature detail of US State Capital
        List : Top Ten Hottest City    */
    @PostMapping(value = "/getTemperature")
    public List<TopTenHottestCity> getTopSevenHottestCities() throws IOException, ParseException {

        /* variables for GET openweather API */
        double lon = -112.07;
        double lat = 33.44;
        int cnt = 7;
        String appid = "533186028e5059722fef6cfd754089e5";
        String units = "imperial";

        /*  Parse the github JSON file of US State capitals */
        JSONParser jsonParser = new JSONParser();
        Resource resource = resourceLoader.getResource("classpath:static/us-state-capitals.json");
        InputStream input = resource.getInputStream();
        File file = resource.getFile();
        FileReader reader = new FileReader(file);
        Object obj = jsonParser.parse(reader);
        JSONArray cityList = (JSONArray) obj;
        usStateCapitalList = new ArrayList<>();
        cityList.forEach(city -> parseCapitol((JSONObject) city));

        /*  Preparation and storing the US State capitals   */
        weatherService.deleteFromTable();
        for (USStateCapital usStateCapital : usStateCapitalList) {
            weatherService.saveAvgTemperatureByLatLong(usStateCapital.getLon(), usStateCapital.getLat(), cnt, appid, units, usStateCapital.getCity());
        }

        /*  List to store the top 10 US State capitals with highest average temperature and then print  */
        List<TopTenHottestCity> topTenCities = weatherService.getTopTenCities();
        printTopTenHottestCity(topTenCities);
        return topTenCities;
    }

    /* Print Top ten hottest US State capital cities */
    private void printTopTenHottestCity(List<TopTenHottestCity> topTenCities) {

        int count = 1;

        System.out.println("------ Top ten hottest cities in US -------");

        for (TopTenHottestCity topTenHottestCity : topTenCities) {
            System.out.println(count + ")" + " " + topTenHottestCity.getCity() + " -> " + topTenHottestCity.getTemp() + " degree Fahrenheit");
            count++;
        }

        count = 0;
    }

    /* Parse all the State capital cities in US */
    private void parseCapitol(JSONObject capitol) {
        String city = (String) capitol.get("city");
        double lon = (double) capitol.get("lon");
        double lat = (double) capitol.get("lat");
        String state = (String) capitol.get("state");

        DecimalFormat dec = new DecimalFormat("#0.00");

        usStateCapitalList.add(new USStateCapital(city, Double.valueOf(dec.format(lon)), Double.valueOf(dec.format(lat)), state));

    }

    @GetMapping(value = "/hello")
    public String hello() {

        return "hello";
    }

    @PostConstruct
    private void init() throws IOException, ParseException {
        getTopSevenHottestCities();
    }

}
