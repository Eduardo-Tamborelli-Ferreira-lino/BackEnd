package com.example.Model;

public class Reservation {

    private long id;

    private long flightId;

    private long passengerId;

    private String seat;

    public Reservation(long id, long flightId, long passengerId, String seat) {
        this.id = id;
        this.flightId = flightId;
        this.passengerId = passengerId;
        this.seat = seat;
    }

    public Reservation(long flightId, long passengerId, String seat) {
        this.flightId = flightId;
        this.passengerId = passengerId;
        this.seat = seat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(long passengerId) {
        this.passengerId = passengerId;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

}
