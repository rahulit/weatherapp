package backendweatherapp.com.example.backendweatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenWeatherResponse {

    @JsonProperty("daily")
    private List<SevenDaysTemp> daily;

    public OpenWeatherResponse() {
    }

    public OpenWeatherResponse(List<SevenDaysTemp> daily) {
        this.daily = daily;
    }

    public List<SevenDaysTemp> getDaily() {
        return daily;
    }

    public void setDaily(List<SevenDaysTemp> daily) {
        this.daily = daily;
    }
}
