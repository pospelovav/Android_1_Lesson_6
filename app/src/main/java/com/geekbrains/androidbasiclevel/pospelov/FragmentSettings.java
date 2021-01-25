package com.geekbrains.androidbasiclevel.pospelov;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.View.OnClickListener;

public class FragmentSettings extends Fragment implements OnClickListener {
    CheckBox checkWindSpeed;
    CheckBox checkAtmosphericPressure;
    Button btnSelect;
    AutoCompleteTextView textView;
    MainPresenter presenter = MainPresenter.getInstance();

    static FragmentSettings create() {
        FragmentSettings fragment = new FragmentSettings();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_settings, container, false);
        textView = layout.findViewById(R.id.textChangeCityView);
        String[] countries = getResources().getStringArray(R.array.city_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);
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
                if (this.textView.getText() != null && !(this.textView.getText().toString().equals(""))){
                    presenter.setCityName(this.textView.getText().toString());
                } else {
                    presenter.setCityName("");
                }

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(requireActivity(), CoatOfWeatherActivity.class);
                    startActivity(intent);
                }



                break;

            default:
                break;
        }
    }

}
