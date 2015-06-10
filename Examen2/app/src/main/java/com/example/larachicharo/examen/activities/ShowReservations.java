package com.example.larachicharo.examen.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.larachicharo.examen.Database.Reservation;
import com.example.larachicharo.examen.Database.ReservationDataSource;
import com.example.larachicharo.examen.R;

import java.util.List;

public class ShowReservations extends ActionBarActivity{

    TextView showReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_reservations);

        showReservation = (TextView)findViewById(R.id.txt_showReservations);

        ReservationDataSource reservationDataSource = new ReservationDataSource(this);
        reservationDataSource.open();
        List<Reservation> reservationsList = reservationDataSource.getAllReservations();
        reservationDataSource.close();

        for(int i=0; i < reservationsList.size(); i++){
            Reservation currentReservation = reservationsList.get(i);
            Log.d("currentReservation", String.valueOf(currentReservation.getNumber()));
            showReservation.setText(
                    currentReservation.getName() + " " + String.valueOf(currentReservation.getNumber()));
        }
    }
}
