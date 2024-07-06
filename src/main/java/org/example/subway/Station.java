package org.example.subway;

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

    public int getStopTime() {
        return stopTime;
    }

    @Override
    public String toString() {
        return String.format("Station{id=%d, name='%s', stationType='%s', stopTime=%d}",
                id, name, stationType.getType(), stopTime);
    }

}
