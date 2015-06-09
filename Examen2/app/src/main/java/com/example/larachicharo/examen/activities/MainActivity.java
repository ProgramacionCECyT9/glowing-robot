package com.example.larachicharo.examen.activities;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.larachicharo.examen.R;
import com.example.larachicharo.examen.myGps.GPSTracker;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    EditText edit_nombre;
    EditText edit_numero;
    Button btn_ubicacion;
    Button btn_detenerServicio;
    Button btn_hacerReservacion;
    Button btn_verReservaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_nombre = (EditText)findViewById(R.id.editNombre);
        edit_numero = (EditText)findViewById(R.id.editNumero);

        btn_ubicacion = (Button)findViewById(R.id.btn_obtenerUbicacion);
        btn_ubicacion.setOnClickListener(this);

        btn_detenerServicio = (Button)findViewById(R.id.btn_detenerServicio);
        btn_detenerServicio.setOnClickListener(this);

        btn_hacerReservacion = (Button)findViewById(R.id.btn_reservar);
        btn_hacerReservacion.setOnClickListener(this);

        btn_verReservaciones = (Button)findViewById(R.id.btn_verReservaciones);
        btn_verReservaciones.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_obtenerUbicacion:
                GPSTracker gpsTracker = new GPSTracker(getApplication());
                if (gpsTracker.canGetLocation()) {
                    double latitude = gpsTracker.getLatitude();
                    double longitude = gpsTracker.getLongitude();
                    Toast.makeText(
                            this,
                            "lat: " + String.valueOf(latitude) + " lon: " + String.valueOf(longitude),
                            Toast.LENGTH_LONG).show();
                } else {
                    gpsTracker.showSettingsAlert();
                }
                gpsTracker.stopUsingGPS();
                break;
            case R.id.btn_verReservaciones:
                Intent i = new Intent(this, ShowReservations.class);
                startActivity(i);
                break;
            case R.id.btn_reservar:
                break;
        }
    }
}