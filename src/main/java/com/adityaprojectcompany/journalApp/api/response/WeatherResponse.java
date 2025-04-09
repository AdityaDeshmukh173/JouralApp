package com.adityaprojectcompany.journalApp.api.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse{
//    private Request request;
//    private Location location;
    private Current current;

    @Getter
    @Setter
    public class Current{
        private int temperature;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;

        private int feelslike;

//        private String observation_time;
//        private int weather_code;
//        private List<String> weather_icons;
//        private int wind_speed;
//        private int wind_degree;
//        private String wind_dir;
//        private int pressure;
//        private int precip;
//        private int humidity;
//        private int cloudcover;
//        private int uv_index;
//        private int visibility;
//        private String is_day;
    }
//
//    public class Location{
//        private String name;
//        private String country;
//        private String region;
//        private String lat;
//        private String lon;
//        private String timezone_id;
//        private String localtime;
//        private int localtime_epoch;
//        private String utc_offset;
//    }
//
//    public class Request{
//        private String type;
//        private String query;
//        private String language;
//        private String unit;
//    }



}

