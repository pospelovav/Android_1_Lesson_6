package com.geekbrains.androidbasiclevel.pospelov;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private CheckBox checkWindSpeed;
    private CheckBox checkAtmosphericPressure;
    final MainPresenter presenter = MainPresenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnSelect;
        btnSelect = findViewById(R.id.buttonSelect);
        btnSelect.setOnClickListener(this);
        checkAtmosphericPressure = findViewById(R.id.checkBoxAtmosphericPressure);
        checkWindSpeed = findViewById(R.id.checkBoxWindSpeed);
        if (presenter.isCheckWindSpeed()){
            checkWindSpeed.setChecked(true);
        }
        if (presenter.isCheckAtmosphericPressure()){
            checkAtmosphericPressure.setChecked(true);
        }
    }

    @SuppressLint({"NonConstantResourceId", "WrongConstant"})
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonSelect:
                if (this.checkWindSpeed.isChecked()){
                    presenter.setCheckWindSpeed(true);
                } else {
                    presenter.setCheckWindSpeed(false);
                }
                if (this.checkAtmosphericPressure.isChecked()){
                    presenter.setCheckAtmosphericPressure(true);
                } else {
                    presenter.setCheckAtmosphericPressure(false);
                }
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
                break;
        }
    }

}