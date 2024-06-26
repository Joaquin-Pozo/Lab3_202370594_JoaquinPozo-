package org.example;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subway {

    private int id;
    private String name;
    private List<Train> trains;

    public Subway(int id, String name, List<Train> trains) {
        this.id = id;
        this.name = name;
        this.trains = trains;
    }

    public Subway(int id, String name) {
        this.id = id;
        this.name = name;
        this.trains = new ArrayList<>();
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
                throw new IllegalArgumentException();
            }
        }
        trains.addAll(List.of(newTrains));
    }
}
