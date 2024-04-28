package weather_forecast.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import weather_forecast.service.WeatherService;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/forecast-summary")
    public String getForecastSummary(@RequestParam("client-id") String clientId,
                                     @RequestParam("city") String city) {
        System.out.println("getForecastSummary: Method entry with params clientId: " + clientId + ", city "+city); //can use log as well for logging
        return weatherService.getForecastSummary(clientId, city);
    }

    @GetMapping("/hourly-forecast")
    public String getHourlyForecast(@RequestParam("client-id") String clientId,
                                    @RequestParam("city") String city) {
        System.out.println("getHourlyForecast: Method entry with params clientId: " + clientId + ", city "+city); //can use log as well for logging
        return weatherService.getHourlyForecast(clientId,city);
    }
}
