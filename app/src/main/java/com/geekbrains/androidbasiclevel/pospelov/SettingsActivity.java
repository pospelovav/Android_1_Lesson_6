package com.geekbrains.androidbasiclevel.pospelov;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.os.Bundle;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            FragmentSettings details = new FragmentSettings();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, details)
                    .commit();
        }
    }
}