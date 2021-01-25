package com.geekbrains.androidbasiclevel.pospelov;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import java.util.Objects;

public class FragmentCities extends Fragment implements View.OnClickListener {
    private ListView listView;
    private TextView emptyTextView;
    ImageButton buttonSettings;
    FragmentSettings fragmentSettings;
    private boolean isExistSecondFragment;   //можно ли расположить рядом второй фрагмент
    private int currentPosition = 0;
    MainPresenter presenter = MainPresenter.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_cities, container, false);
        buttonSettings = layout.findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initList();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isExistSecondFragment = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("CurrentCity", 0);

        }

        if (isExistSecondFragment) {
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showCoatOfWeather();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("CurrentCity", currentPosition);
        super.onSaveInstanceState(outState);
    }

    private void initViews(View view) {
        listView = view.findViewById(R.id.cities_list_view);

        emptyTextView = view.findViewById(R.id.cities_list_empty_view);
    }

    private void initList() {
        ArrayAdapter adapter =  ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()), R.array.city_array, android.R.layout.simple_list_item_activated_1);
        listView.setAdapter(adapter);
        listView.setEmptyView(emptyTextView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPosition = position;
                presenter.setCityName("");

                if (fragmentSettings != null){
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), MainActivity.class);
                    intent.putExtra("index", getCoatContainer());
                    startActivity(intent);
                } else {
                    showCoatOfWeather();
                }
            }
        });

    }

    private void showCoatOfWeather() {
        //listView.setItemChecked(currentPosition, true);
        listView.setItemChecked(currentPosition, false);
        if (isExistSecondFragment) {
            CoatOfWeatherFragment detail = (CoatOfWeatherFragment)  getFragmentManager().findFragmentById(R.id.coat_of_weather);
            if (detail == null || detail.getIndex() != currentPosition) {
                detail = CoatOfWeatherFragment.create(getCoatContainer());
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                ft.replace(R.id.coat_of_weather, detail);  // замена фрагмента
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack("Some_Key");
                ft.commit();
            }

        } else {

            Intent intent = new Intent();
            intent.setClass(getActivity(), CoatOfWeatherActivity.class);
            intent.putExtra("index", getCoatContainer());
            startActivity(intent);
        }
    }

    private CoatContainer getCoatContainer() {
        String[] cities = this.getResources().getStringArray(R.array.city_array);
        CoatContainer container = new CoatContainer();
        container.position = currentPosition;
        container.cityName = cities[currentPosition];
        return container;
    }

    private void showSettings(){
        if (isExistSecondFragment) {
                fragmentSettings = FragmentSettings.create();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.coat_of_weather, fragmentSettings);  // замена фрагмента
                ft.commit();
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), SettingsActivity.class);
            startActivity(intent);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSettings:
                    showSettings();
                break;
            case R.id.buttonSelect:
                    showCoatOfWeather();
                break;
            default:
                break;
        }
    }




}
