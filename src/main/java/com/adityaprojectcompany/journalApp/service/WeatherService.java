package com.adityaprojectcompany.journalApp.service;

import com.adityaprojectcompany.journalApp.Cache.AppCache;
import com.adityaprojectcompany.journalApp.Entity.User;
import com.adityaprojectcompany.journalApp.api.response.WeatherResponse;
import com.adityaprojectcompany.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apikey ;

//    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY" ;       (Done in ConfigJournalApp)

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache ;

    @Autowired
    private RedisService redisService ;

    public WeatherResponse getWeather(String city){

        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if (weatherResponse != null){
            return weatherResponse ;
        }else {
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY,city).replace(Placeholders.API_KEY,apikey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null){
                redisService.set("weather_of_" + city, body,300l);
            }
            return body ;
        }

//        This is how the data for POST call will be sent
//        String requestBody = "{\n" +
//                "    \"userName\":\"vipul\",\n" +
//                "    \"password\":\"vipul\"\n" +
//                "}" ;
//        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody);
// OR   It also can be done like
//        User user = User.builder().userName("Vipul").password("Vipul").build();
//        HttpEntity<User> httpEntity = new HttpEntity<>(user,httpHeaders) ;

//        Header is sent as a key value pair with the
//        HttpHeaders httpHeaders = new HttpHeaders() ;
//        httpHeaders.set("Key","Value");           send this in httpEntity

//              The POST call from POSTMAN app can be (sent like this) done as the following code
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, HttpEntity, WeatherResponse.class);
        //        response.getStatusCode();

//        String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace("<city>",city).replace("<apiKey>",apikey);
//        String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY,city).replace(Placeholders.API_KEY,apikey);
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
//        WeatherResponse body = response.getBody();
//        return body ;
    }

}
