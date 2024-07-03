package org.example.metro;

public class Station {
    private int id;
    private String name;
    private StationType stationType;
    private int stopTime;

    public Station(int id, String name, StationType stationType, int stopTime) {
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

    public StationType getStationType() {
        return stationType;
    }

    public int getStopTime() {
        return stopTime;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", stationType = " + stationType.getType() +
                ", stopTime = " + stopTime +
                '}';
    }
}
