package com.example.Model;

public class Airport {

    private long id;

    private String name;

    private String IataCode;

    public Airport(long id, String name, String iataCode) {
        this.id = id;
        this.name = name;
        IataCode = iataCode;
    }

    public Airport(String name, String iataCode) {
        this.name = name;
        IataCode = iataCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIataCode() {
        return IataCode;
    }

    public void setIataCode(String iataCode) {
        IataCode = iataCode;
    }

}
