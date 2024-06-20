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

    public void addPcar(PassengerCar pcar, int position) {
        List<PassengerCar> pcars = getPcars();
        if (position < 0 || position > pcars.size()) {
            throw new IndexOutOfBoundsException();
        }
        pcars.add(position, pcar);
    }


    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", trainMaker='" + trainMaker + '\'' +
                ", speed=" + speed +
                ", stationStayTime=" + stationStayTime +
                ", pcars=" + pcars +
                '}';
    }
}


