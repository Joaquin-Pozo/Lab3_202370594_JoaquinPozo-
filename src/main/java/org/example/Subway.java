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

    public Subway(int id, String name) {
        this.id = id;
        this.name = name;
        this.trains = new ArrayList<>();
        this.lines = new ArrayList<>();
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

    @Override
    public String toString() {
        return "Subway{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", \n\ntrains=" + trains +
                "\n\n, lines=" + lines +
                '}';
    }
}
