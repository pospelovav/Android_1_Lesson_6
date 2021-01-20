package com.geekbrains.androidbasiclevel.pospelov;

public final class MainPresenter {
    private static MainPresenter instance = null;
    private static final Object syncObj = new Object();
    private String cityName;
    private boolean checkWindSpeed;   //для чекера скорость ветра
    private boolean checkAtmosphericPressure;  //для чекера атм.давление
    private int degreesToday;
    private int degreesTomorrow;

    private MainPresenter(){
        degreesToday = -5;   //для теста
        degreesTomorrow = 2;
    }

    public void setCityName(String cityName){
        this.cityName = cityName;
    }
    public void setCheckAtmosphericPressure(boolean checkAtmosphericPressure) {
        this.checkAtmosphericPressure = checkAtmosphericPressure;
    }
    public void setCheckWindSpeed(boolean checkWindSpeed) {
        this.checkWindSpeed = checkWindSpeed;
    }
    public void setDegreesToday(int degreesToday) {
        this.degreesToday = degreesToday;
    }
    public void setDegreesTomorrow(int degreesTomorrow) {
        this.degreesTomorrow = degreesTomorrow;
    }

    public String getCityName() {
        return cityName;
    }
    public boolean isCheckWindSpeed() {
        return checkWindSpeed;
    }
    public boolean isCheckAtmosphericPressure() {
        return checkAtmosphericPressure;
    }
    public int getDegreesToday() {
        return degreesToday;
    }
    public int getDegreesTomorrow() {
        return degreesTomorrow;
    }

    public static MainPresenter getInstance(){
        synchronized (syncObj) {
            if (instance == null) {
                instance = new MainPresenter();
            }
            return instance;
        }
    }

}
