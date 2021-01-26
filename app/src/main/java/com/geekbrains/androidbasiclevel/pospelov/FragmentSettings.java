package com.geekbrains.androidbasiclevel.pospelov;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class FragmentSettings extends Fragment implements OnClickListener {
    CheckBox checkWindSpeed;
    CheckBox checkAtmosphericPressure;
    Button btnSelect;

    TextInputEditText textInput;
    MainPresenter presenter = MainPresenter.getInstance();

    static FragmentSettings create() {
        FragmentSettings fragment = new FragmentSettings();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_settings, container, false);
        textInput = layout.findViewById(R.id.cityName);
        btnSelect = layout.findViewById(R.id.buttonSelect);
        btnSelect.setOnClickListener(this);
        checkAtmosphericPressure = layout.findViewById(R.id.checkBoxAtmosphericPressure);
        checkWindSpeed = layout.findViewById(R.id.checkBoxWindSpeed);
        if (presenter.isCheckWindSpeed()){
            checkWindSpeed.setChecked(true);
        }
        if (presenter.isCheckAtmosphericPressure()){
            checkAtmosphericPressure.setChecked(true);
        }


        return layout;
    }

    @SuppressLint("NonConstantResourceId")
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
                if (this.textInput.getText() != null && !(this.textInput.getText().toString().equals(""))){
                    presenter.setCityName(this.textInput.getText().toString());
                } else {
                    presenter.setCityName("");
                }

                Snackbar.make(v, getResources().getString(R.string.snackbarText) + ": " + this.textInput.getText().toString(), Snackbar.LENGTH_LONG)
                        .setAction(getResources().getString(R.string.snackbarButton), new OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                    Intent intent = new Intent();
                                    intent.setClass(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Intent intent = new Intent();
                                    intent.setClass(requireActivity(), CoatOfWeatherActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }).show();

                break;

            default:
                break;
        }
    }


}
