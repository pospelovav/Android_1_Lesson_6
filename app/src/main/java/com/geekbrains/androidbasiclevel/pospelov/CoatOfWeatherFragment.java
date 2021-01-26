package com.geekbrains.androidbasiclevel.pospelov;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class CoatOfWeatherFragment extends Fragment implements OnClickListener {
    View layout;
    TextView textViewCity;
    MaterialButton btnInfo;
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
        if ((presenter.getCityName() != null) && !(presenter.getCityName().equals(""))) {
            return presenter.getCityName();
        } else {

            CoatContainer coatContainer = (CoatContainer) (Objects.requireNonNull(getArguments()).getSerializable("index"));
            try {
                return coatContainer.cityName;
            } catch (Exception e) {
                return "";
            }
        }
    }

    @Override
    @SuppressLint("Recycle")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.activity_weather, container, false);

        textViewCity = layout.findViewById(R.id.cityView);
        String cityName = getCityName();
        textViewCity.setText(cityName);

        btnInfo = layout.findViewById(R.id.imageButtonInfo);
        btnInfo.setOnClickListener(this);

        SocSource sourceData = new SocSource(getResources());
        initRecyclerView(sourceData.build());
        return layout;
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private void initRecyclerView(SocSource sourceData){
        RecyclerView recyclerView = layout.findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());


        SocnetAdapter adapter = new SocnetAdapter(sourceData);


        DividerItemDecoration itemDecoration = new DividerItemDecoration(Objects.requireNonNull(getActivity()),  LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getActivity().getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSettings:
                Intent intent = new Intent();
                intent.setClass(getActivity(), SettingsActivity.class);
                startActivity(intent);
                break;

            case R.id.imageButtonInfo:                                         //открытие браузера с поиском информации по городу в Яндексе
                final TextView textViewCity = layout.findViewById(R.id.cityView);
                String url = "https://yandex.ru/search/?text=" + textViewCity.getText().toString();
                Uri uri = Uri.parse(url);
                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browser);
                break;

            default:
                break;
        }
    }

}
