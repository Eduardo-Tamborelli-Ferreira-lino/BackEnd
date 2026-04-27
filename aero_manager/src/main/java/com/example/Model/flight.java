package com.example.Model;

import java.time.LocalDate;

public class flight {

    private long id;

    private long planeId;

    private long originId;

    private long destinationId;

    private LocalDate time;

    public flight(long id, long planeId, long originId, long destinationId, LocalDate time) {
        this.id = id;
        this.planeId = planeId;
        this.originId = originId;
        this.destinationId = destinationId;
        this.time = time;
    }

    public flight(long planeId, long originId, long destinationId, LocalDate time) {
        this.planeId = planeId;
        this.originId = originId;
        this.destinationId = destinationId;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlaneId() {
        return planeId;
    }

    public void setPlaneId(long planeId) {
        this.planeId = planeId;
    }

    public long getOriginId() {
        return originId;
    }

    public void setOriginId(long originId) {
        this.originId = originId;
    }

    public long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(long destinationId) {
        this.destinationId = destinationId;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

}
