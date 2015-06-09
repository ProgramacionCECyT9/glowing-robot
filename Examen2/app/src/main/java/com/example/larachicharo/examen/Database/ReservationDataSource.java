package com.example.larachicharo.examen.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ReservationDataSource {

    private SQLiteDatabase database;
    private mySQLHelper dbHelper;
    private String[] allColumns = { mySQLHelper.COLUMN_ID,
            mySQLHelper.COLUMN_NAME,  mySQLHelper.COLUMN_NUMBER, mySQLHelper.COLUMN_LOCATION};

    public ReservationDataSource(Context context) {
        dbHelper = new mySQLHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Reservation createReservation(String reservation) {
        ContentValues values = new ContentValues();
        values.put(mySQLHelper.COLUMN_NAME, reservation);
        long insertId = database.insert(mySQLHelper.TABLE_RERSERVATIONS, null,
                values);
        Cursor cursor = database.query(mySQLHelper.TABLE_RERSERVATIONS,
                allColumns, mySQLHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Reservation newReservation = cursorToReservation(cursor);
        cursor.close();
        return newReservation;
    }

    public List<Reservation> getAllComments() {
        List<Reservation> reservations = new ArrayList<Reservation>();

        Cursor cursor = database.query(mySQLHelper.TABLE_RERSERVATIONS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Reservation reservation = cursorToReservation(cursor);
            reservations.add(reservation);
            cursor.moveToNext();
        }
        cursor.close();
        return reservations;
    }

    /** Delete? **/
    public void deleteReservation(Reservation reservation) {
        long id = reservation.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(mySQLHelper.TABLE_RERSERVATIONS, mySQLHelper.COLUMN_ID
                + " = " + id, null);
    }


    private Reservation cursorToReservation(Cursor cursor) {
        Reservation Reservation = new Reservation();
        Reservation.setId(cursor.getLong(0));
        Reservation.setName(cursor.getString(1));
        return Reservation;
    }

}
