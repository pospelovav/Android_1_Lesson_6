package com.geekbrains.androidbasiclevel.pospelov;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;


public class CoatOfWeatherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        MaterialButton btnInfo;
        btnInfo = findViewById(R.id.imageButtonInfo);
        btnInfo.setVisibility(View.GONE);
        TextView textName = findViewById(R.id.cityView);
        textName.setVisibility(View.GONE);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            CoatOfWeatherFragment details = new CoatOfWeatherFragment();
            details.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, details)
                    .commit();
        }
    }


}
