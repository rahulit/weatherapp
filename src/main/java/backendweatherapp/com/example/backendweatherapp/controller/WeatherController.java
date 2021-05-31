package backendweatherapp.com.example.backendweatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/")
public class WeatherController {


    @GetMapping(value = "/hello")
    public String hello() {

        return "hello";
    }

}
