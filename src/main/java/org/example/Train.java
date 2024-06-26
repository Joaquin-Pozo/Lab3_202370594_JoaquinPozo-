package org.example;

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

    public void addPcar(PassengerCar pcar, int position) {
        List<PassengerCar> pcars = getPcars();
        if (position < 0 || position > pcars.size()) {
            throw new IndexOutOfBoundsException();
        }
        pcars.add(position, pcar);
    }

    public void removePcar(Train train, int position) {
        List <PassengerCar> pcars = train.getPcars();
        if (position < 0 || position > pcars.size()) {
            throw new IndexOutOfBoundsException();
        }
        pcars.remove(position);
    }

    public boolean isTrain() {
        List <PassengerCar> pcars = getPcars();
        boolean isTrue = true;
        if (pcars == null) {
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
        return "\nTrain{" +
                "id=" + id +
                ", trainMaker='" + trainMaker + '\'' +
                ", speed=" + speed +
                ", stationStayTime=" + stationStayTime +
                ", pcars=" + pcars +
                "\n}";
    }
}


