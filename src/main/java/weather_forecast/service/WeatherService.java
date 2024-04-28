package weather_forecast.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final String rapidApiKey;
    private final String rapidApiHost;

    public WeatherService(RestTemplate restTemplate,
                          @Value("${rapidapi.key}") String rapidApiKey, @Value("${rapidapi.host}") String rapidApiHost) {
        this.restTemplate = restTemplate;
        this.rapidApiKey = rapidApiKey;
        this.rapidApiHost = rapidApiHost;
    }

    public String getForecastSummary(String clientId, String city) {
    if (!authenticate(clientId)) {
            return "Unauthorized user. clientId is null or empty";
        }
    try {
        HttpHeaders headers = createHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://forecast9.p.rapidapi.com/rapidapi/forecast/" + city + "/summary/",
                HttpMethod.GET,
                entity,
                String.class);

        return response.getBody();
    }catch (Exception e){
        return "Something went wrong while making rest call to Rapid api... Exception: "+e.getMessage();
    }
    }

    public String getHourlyForecast(String clientId, String city) {
        if (!authenticate(clientId)) {
            return "Unauthorized user. clientId is null or empty";
        }
        try{

        HttpHeaders headers = createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://forecast9.p.rapidapi.com/rapidapi/forecast/" + city + "/hourly/",
                HttpMethod.GET,
                entity,
                String.class);

        return response.getBody();
        }catch (Exception e){
            return "Something went wrong while making rest call to Rapid api... Exception: "+e.getMessage();
        }
    }

    private boolean authenticate(String clientId) {
        return (clientId != null && !clientId.trim().isEmpty());
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", rapidApiKey);
        headers.set("x-rapidapi-host", rapidApiHost);
        return headers;
    }
}
