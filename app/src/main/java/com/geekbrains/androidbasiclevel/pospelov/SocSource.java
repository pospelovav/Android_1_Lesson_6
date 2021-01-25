package com.geekbrains.androidbasiclevel.pospelov;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

public class SocSource {
    private List<Soc> dataSource;
    private Resources resources;

    public SocSource(Resources resources) {
        this.dataSource = new ArrayList<>(10);
        this.resources = resources;
    }

    public SocSource build(){
        String[] date = resources.getStringArray(R.array.textDateView);
        String[] weatherText = resources.getStringArray(R.array.textWeatherView);
        String[] temperature = resources.getStringArray(R.array.textTempView);
        int[] weatherPicture = getIndexTextArray();
        int[] windSpeed = resources.getIntArray(R.array.windSpeedView);
        int[] airPressure = resources.getIntArray(R.array.airPressureView);

        for (int i = 0; i < date.length; i++) {
            this.dataSource.add(new Soc(date[i], weatherPicture[i], weatherText[i], temperature[i], windSpeed[i], airPressure[i]));
        }
        return this;
    }

    public Soc getSoc(int position) {
        return dataSource.get(position);
    }

    public int size(){
        return dataSource.size();
    }

    private int[] getIndexTextArray(){
        TypedArray pictures = resources.obtainTypedArray(R.array.imageWeatherView);
        int length = pictures.length();
        int[] answer = new int[length];
        for(int i = 0; i < length; i++){
            answer[i] = pictures.getResourceId(i, 0);
        }
        return answer;

    }


}
