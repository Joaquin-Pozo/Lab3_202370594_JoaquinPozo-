package org.example.subway;

import java.util.ArrayList;
import java.util.List;

public class Train {
    private int id;
    private String trainMaker;
    private int speed;
    private int stationStayTime;
    private List<PassengerCar> pcars;

    public Train(int id, String trainMaker, int speed, int stationStayTime, List<PassengerCar> pcars) {
        this.id = id;
        this.trainMaker = trainMaker;
        this.speed = speed;
        this.stationStayTime = stationStayTime;
        if (pcars != null) {
            this.pcars = new ArrayList<>(pcars);
        } else {
            this.pcars = new ArrayList<>();
        }
    }

    public List<PassengerCar> getPcars() {
        return pcars;
    }

    public int getId() {
        return id;
    }

    public String getTrainMaker() {
        return trainMaker;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStationStayTime() {
        return stationStayTime;
    }

    public void addPcar(PassengerCar pcar, int position) {
        List<PassengerCar> pcars = getPcars();
        if (position < 0 || position > pcars.size()) {
            throw new IndexOutOfBoundsException("La posición ingresada es inválida");
        }
        pcars.add(position, pcar);
    }

    public void removePcar(Train train, int position) {
        List <PassengerCar> pcars = train.getPcars();
        if (position < 0 || position > pcars.size()) {
            throw new IndexOutOfBoundsException("La posición ingresada es inválida");
        }
        pcars.remove(position);
    }

    public boolean isTrain() {
        List <PassengerCar> pcars = getPcars();
        boolean isTrue = true;
        if (pcars.isEmpty() || pcars.size() < 2) {
            return false;
        }
        PassengerCar primero = pcars.get(0);
        PassengerCar ultimo = pcars.get(pcars.size() - 1);
        if (!primero.getCarType().getType().equals("tr") || !ultimo.getCarType().getType().equals("tr")) {
            isTrue = false;
        }
        for (int i = 1; i < pcars.size() - 1; i++) {
            if (!pcars.get(i).getCarType().getType().equals("ct")) {
                isTrue = false;
                break;
            }
        }
        for (int i = 0; i < pcars.size() - 1; i++) {
            String model1 = pcars.get(i).getModel();
            String trainMaker1 = pcars.get(i).getTrainMaker();
            for (int j = 1; j < pcars.size(); j++) {
                String model2 = pcars.get(j).getModel();
                String trainMaker2 = pcars.get(j).getTrainMaker();
                if (!model1.equals(model2) || !trainMaker1.equals(trainMaker2)) {
                    isTrue = false;
                    break;
                }
            }
        }
        return isTrue;
    }

    public int fetchCapacity() {
        List<PassengerCar> pcars = getPcars();
        int capacity = 0;
        for (PassengerCar pcar : pcars) {
            capacity = pcar.getPassengerCapacity() + capacity;
        }
        return capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Train{id=%d, maker='%s', speed=%d, stayTime=%d, cars=[", id, trainMaker, speed, stationStayTime));
        if (pcars.isEmpty()) {
            sb.append("No passenger cars available");
        } else {
            for (PassengerCar car : pcars) {
                sb.append("\n  ").append(car);
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}


