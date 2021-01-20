package com.geekbrains.androidbasiclevel.pospelov;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class CoatOfWeatherFragment extends Fragment {
    TextView textViewCity;
    TextView textViewWindSpeedToday;
    TextView textViewAirPressureToday;
    TextView airPressureTodayView;
    TextView windSpeedTodayView;
    TextView textViewWindSpeedTomorrow;
    TextView windSpeedTomorrowView;
    TextView textViewAirPressureTomorrow;
    TextView airPressureTomorrowView;
    TextView textTempToday;
    TextView textTempTomorrow;
    MainPresenter presenter = MainPresenter.getInstance();

    static CoatOfWeatherFragment create(CoatContainer container) {
        CoatOfWeatherFragment fragment = new CoatOfWeatherFragment();
        Bundle args = new Bundle();
        args.putSerializable("index", container);
        fragment.setArguments(args);
        return fragment;
    }

    int getIndex() {
        CoatContainer coatContainer = (CoatContainer) (getArguments()).getSerializable("index");

        try {
            return coatContainer.position;
        } catch (Exception e) {
            return 0;
        }
    }

    String getCityName() {
        CoatContainer coatContainer = (CoatContainer) (Objects.requireNonNull(getArguments()).getSerializable("index"));

        try {
            return coatContainer.cityName;
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    @SuppressLint("Recycle")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_coat_of_weather, container, false);
        textViewCity = layout.findViewById(R.id.cityView);

        String cityName = getCityName();
        textViewCity.setText(cityName);

        TextView textTodayDate = layout.findViewById(R.id.textTodayDateView);
        Calendar today = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat("E, dd MMM");
        textTodayDate.setText(df.format(today.getTime()));
        TextView textTomorrowDate = layout.findViewById(R.id.textTomorrowDateView);
        today.roll(Calendar.DAY_OF_MONTH, +1);
        textTomorrowDate.setText(df.format(today.getTime()));

        textViewWindSpeedToday =  layout.findViewById(R.id.textViewWindSpeedToday);
        windSpeedTodayView =  layout.findViewById(R.id.windSpeedTodayView);
        textViewAirPressureToday = layout.findViewById(R.id.textViewAirPressureToday);
        airPressureTodayView = layout.findViewById(R.id.airPressureTodayView);
        textViewWindSpeedTomorrow = layout.findViewById(R.id.textViewWindSpeedTomorrow);
        windSpeedTomorrowView = layout.findViewById(R.id.windSpeedTomorrowView);
        textViewAirPressureTomorrow = layout.findViewById(R.id.textViewAirPressureTomorrow);
        airPressureTomorrowView = layout.findViewById(R.id.airPressureTomorrowView);
        textTempToday = layout.findViewById(R.id.textTempTodayView);
        textTempTomorrow = layout.findViewById(R.id.textTempTomorrowView);

        textTempToday.setText(presenter.getDegreesToday() + " °C");
        textTempTomorrow.setText(presenter.getDegreesTomorrow() + " °C");

        if (presenter.isCheckWindSpeed()){
            textViewWindSpeedToday.setVisibility(View.VISIBLE);
            windSpeedTodayView.setVisibility(View.VISIBLE);
            textViewWindSpeedTomorrow.setVisibility(View.VISIBLE);
            windSpeedTomorrowView.setVisibility(View.VISIBLE);
        } else {
            textViewWindSpeedToday.setVisibility(View.GONE);
            windSpeedTodayView.setVisibility(View.GONE);
            textViewWindSpeedTomorrow.setVisibility(View.GONE);
            windSpeedTomorrowView.setVisibility(View.GONE);
        }
        if (presenter.isCheckAtmosphericPressure()){
            textViewAirPressureToday.setVisibility(View.VISIBLE);
            airPressureTodayView.setVisibility(View.VISIBLE);
            textViewAirPressureTomorrow.setVisibility(View.VISIBLE);
            airPressureTomorrowView.setVisibility(View.VISIBLE);
        } else {
            textViewAirPressureToday.setVisibility(View.GONE);
            airPressureTodayView.setVisibility(View.GONE);
            textViewAirPressureTomorrow.setVisibility(View.GONE);
            airPressureTomorrowView.setVisibility(View.GONE);
        }
        return layout;
    }
}
