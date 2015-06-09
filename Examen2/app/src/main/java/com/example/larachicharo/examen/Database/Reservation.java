package com.example.larachicharo.examen.Database;

public class Reservation {

    private long id;
    private String name;
    private int number;
    private String location;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getLocation() {
        return location;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
