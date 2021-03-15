package com.example.weather_app3;

import java.io.Serializable;

public class
ModelClass  {
    public String city;
    public String date_time;
    public String temp;
    public String humidity;
    public String weather;
    public String description;
    public String icon;
    public String Max_temp;
    public String Min_temp;
    public String wind;


    public ModelClass(String city, String date_time, String temp, String humidity, String weather, String description, String icon, String max_temp, String min_temp, String wind) {
        this.city = city;
        this.date_time = date_time;
        this.temp = temp;
        this.humidity = humidity;
        this.weather = weather;
        this.description = description;
        this.icon = icon;
        Max_temp = max_temp;
        Min_temp = min_temp;
        this.wind = wind;

    }

    public ModelClass(String city, String date_time, String temp, String humidity, String weather, String description, String icon) {
        this.city = city;
        this.date_time = date_time;
        this.temp = temp;
        this.humidity = humidity;
        this.weather = weather;
        this.description = description;
        this.icon = icon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
