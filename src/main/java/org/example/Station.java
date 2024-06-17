package org.example;

public class Station {
    int id;
    String name;
    Type stationType;
    int stopTime;

    public Station(int id, String name, Type stationType, int stopTime) {
        this.id = id;
        this.name = name;
        this.stationType = stationType;
        this.stopTime = stopTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Type getStationType() {
        return stationType;
    }

    public int getStopTime() {
        return stopTime;
    }
}
