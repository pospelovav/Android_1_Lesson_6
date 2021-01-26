package com.geekbrains.androidbasiclevel.pospelov;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SocnetAdapter extends RecyclerView.Adapter <SocnetAdapter.ViewHolder> {

    private SocSource dataSource;


    public SocnetAdapter(SocSource dataSource){
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public SocnetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weather, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SocnetAdapter.ViewHolder viewHolder, int i) {

        Soc soc = dataSource.getSoc(i);
        viewHolder.setData(soc.getDate(), soc.getWeatherPicture(), soc.getWeatherText(), soc.getTemperature(), soc.getWindSpeed(), soc.getAirPressure());

    }


    @Override
    public int getItemCount() {
        return dataSource.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textDateView;
        private ImageView imageWeatherView;
        private TextView textTempView;
        private TextView textWeatherView;
        private TextView textViewWindSpeed;
        private TextView windSpeedView;
        private TextView textViewAirPressure;
        private TextView airPressureView;
        MainPresenter presenter = MainPresenter.getInstance();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textDateView = (TextView) itemView.findViewById(R.id.textDateView);
            imageWeatherView = (ImageView) itemView.findViewById(R.id.imageWeatherView);
            textTempView = (TextView) itemView.findViewById(R.id.textTempView);
            textWeatherView = (TextView) itemView.findViewById(R.id.textWeatherView);
            textViewWindSpeed = (TextView) itemView.findViewById(R.id.textViewWindSpeed);
            windSpeedView = (TextView) itemView.findViewById(R.id.windSpeedView);
            textViewAirPressure = (TextView) itemView.findViewById(R.id.textViewAirPressureToday);
            airPressureView = (TextView) itemView.findViewById(R.id.airPressureView);


            if (presenter.isCheckWindSpeed()){
                textViewWindSpeed.setVisibility(View.VISIBLE);
                windSpeedView.setVisibility(View.VISIBLE);
            } else {
                textViewWindSpeed.setVisibility(View.GONE);
                windSpeedView.setVisibility(View.GONE);
            }
            if (presenter.isCheckAtmosphericPressure()){
                textViewAirPressure.setVisibility(View.VISIBLE);
                airPressureView.setVisibility(View.VISIBLE);

            } else {
                textViewAirPressure.setVisibility(View.GONE);
                airPressureView.setVisibility(View.GONE);

            }
        }

        public void setData(String date, int weatherPicture, String weatherText, String temperature, int windSpeed, int airPressure){
            getDate().setText(date);
            getImageWeatherView().setImageResource(weatherPicture);
            getTextWeatherView().setText(weatherText);
            getTextTempView().setText(temperature);
            getWindSpeedView().setText(Integer.toString(windSpeed));
            getAirPressureView().setText(Integer.toString(airPressure));

        }

        public TextView getDate() {
            return textDateView;
        }
        public ImageView getImageWeatherView() {
            return imageWeatherView;
        }
        public TextView getTextTempView() {
            return textTempView;
        }
        public TextView getTextWeatherView() {
            return textWeatherView;
        }
        public TextView getAirPressureView() {
            return airPressureView;
        }
        public TextView getWindSpeedView() {
            return windSpeedView;
        }

    }
}

