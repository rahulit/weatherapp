package backendweatherapp.com.example.backendweatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class USStateCapital {

    private String city;
    private double lon;
    private double lat;
    private String state;

    @JsonIgnore
    private double averageTempOfCity;

    public USStateCapital() {
    }


    public USStateCapital(String city, double lon, double lat, String state) {
        this.city = city;
        this.lon = lon;
        this.lat = lat;
        this.state = state;
    }

    public USStateCapital(String city, double averageTempOfCity) {
        this.city = city;
        this.averageTempOfCity = averageTempOfCity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getAverageTempOfCity() {
        return averageTempOfCity;
    }

    public void setAverageTempOfCity(double averageTempOfCity) {
        this.averageTempOfCity = averageTempOfCity;
    }
}
