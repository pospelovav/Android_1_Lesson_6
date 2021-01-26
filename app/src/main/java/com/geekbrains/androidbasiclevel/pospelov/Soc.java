package com.geekbrains.androidbasiclevel.pospelov;

public class Soc {
    private String date;
    private int weatherPicture;
    private String weatherText;
    private String temperature;
    private int windSpeed;
    private int airPressure;

    public Soc(String date, int weatherPicture, String weatherText, String temperature, int windSpeed, int airPressure){
        this.date = date;
        this.weatherPicture = weatherPicture;
        this.weatherText = weatherText;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.airPressure = airPressure;
    }

    public int getAirPressure() {
        return airPressure;
    }

    public int getWeatherPicture() {
        return weatherPicture;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public String getDate() {
        return date;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWeatherText() {
        return weatherText;
    }

}

