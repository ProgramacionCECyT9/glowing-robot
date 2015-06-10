package com.example.larachicharo.examen.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.larachicharo.examen.Database.Reservation;
import com.example.larachicharo.examen.Database.ReservationDataSource;
import com.example.larachicharo.examen.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ShowReservations extends ActionBarActivity{

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_reservations);

        ReservationDataSource reservationDataSource = new ReservationDataSource(this);
        reservationDataSource.open();
        List<Reservation> reservationsList = reservationDataSource.getAllReservations();
        reservationDataSource.close();

        ArrayList<String> stringArray = new ArrayList<String>();
        for (int i=0; i<reservationsList.size(); i++){
            String name = reservationsList.get(i).getName();
            String number = reservationsList.get(i).getNumber();
            String location = reservationsList.get(i).getLocation();

            stringArray.add(i, gatherStrings(name, number, location));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringArray);
        listview = (ListView)findViewById(R.id.list_view);
        listview.setAdapter(adapter);   


        }
        public String gatherStrings(String name, String number, String location){
            return name + " " + number + " " + location;
        }
    }

