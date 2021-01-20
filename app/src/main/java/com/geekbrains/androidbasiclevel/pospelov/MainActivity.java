package com.geekbrains.androidbasiclevel.pospelov;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int countOfFragmentInManager = getSupportFragmentManager().getBackStackEntryCount();
        if(countOfFragmentInManager > 0) {
            getSupportFragmentManager().popBackStack();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSettings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;

            case R.id.imageButtonInfo:                                         //открытие браузера с поиском информации по городу в Яндексе
                final TextView textViewCity = findViewById(R.id.cityView);
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