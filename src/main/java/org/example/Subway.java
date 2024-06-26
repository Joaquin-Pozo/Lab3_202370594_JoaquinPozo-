package org.example;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subway {

    private int id;
    private String name;
    private List<Train> trains;
    private List<Line> lines;
    private List<Driver> drivers;

    public Subway(int id, String name) {
        this.id = id;
        this.name = name;
        this.trains = new ArrayList<>();
        this.lines = new ArrayList<>();
        this.drivers = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void addTrain(Train... newTrains) {
        Set<Integer> trainIds = new HashSet<>();

        for (Train train: trains) {
            trainIds.add(train.getId());
        }
        Set<Integer> newTrainIds = new HashSet<>();
        for (Train newTrain: newTrains) {
            if (trainIds.contains(newTrain.getId()) || !newTrainIds.add(newTrain.getId()) || !newTrain.isTrain()) {
                throw new IllegalArgumentException("Tren inválido");
            }
        }
        trains.addAll(List.of(newTrains));
    }

    public void addLine(Line... newLines) {
        Set<Integer> lineIds = new HashSet<>();

        for (Line line: lines) {
            lineIds.add(line.getId());
        }
        Set<Integer> newLineIds = new HashSet<>();
        for (Line newLine: newLines) {
            if (lineIds.contains(newLine.getId()) || !newLineIds.add(newLine.getId()) || !newLine.isLine(newLine)) {
                throw new IllegalArgumentException("Línea inválida");
            }
        }
        lines.addAll(List.of(newLines));
    }

    public void addDriver(Driver... newDrivers) {
        Set<Integer> driverIds = new HashSet<>();

        for (Driver driver: drivers) {
            driverIds.add(driver.getId());
        }
        Set<Integer> newDriverIds = new HashSet<>();
        for (Driver newDriver : newDrivers) {
            if (driverIds.contains(newDriver.getId()) || !newDriverIds.add(newDriver.getId())) {
                throw new IllegalArgumentException("Conductor inválido");
            }
        }
        drivers.addAll(List.of(newDrivers));
    }


    @Override
    public String toString() {
        return "Subway{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", trains=" + trains +
                ", lines=" + lines +
                ", drivers=" + drivers +
                '}';
    }
}
