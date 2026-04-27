package com.example.Model;

public class Plane {

    private long id;

    private String model;

    private long capacity;

    public Plane(long id, String model, long capacity) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
    }

    public Plane(String model, long capacity) {
        this.model = model;
        this.capacity = capacity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

}
