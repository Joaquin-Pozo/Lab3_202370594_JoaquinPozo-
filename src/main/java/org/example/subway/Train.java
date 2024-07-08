package org.example.subway;

import java.util.ArrayList;
import java.util.List;

public class Train {
    private int id;
    private String trainMaker;
    private int speed;
    private int stationStayTime;
    private List<PassengerCar> pcars;

    /**
     * metodo constructor
     * @param id
     * @param trainMaker
     * @param speed
     * @param stationStayTime
     * @param pcars
     */
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

    /**
     * metodo para obtener lista de carros
     * @return
     */
    public List<PassengerCar> getPcars() {
        return pcars;
    }

    /**
     * metodo para obtener id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * metodo para obtener fabricante
     * @return
     */
    public String getTrainMaker() {
        return trainMaker;
    }

    /**
     * metodo para obtener velocidad
     * @return
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * metodo para obtener tiempo de parada por estacion
     * @return
     */
    public int getStationStayTime() {
        return stationStayTime;
    }

    /**
     * metodo para agregar un carro en una posicion especificada
     * @param pcar
     * @param position
     */
    public void addPcar(PassengerCar pcar, int position) {
        List<PassengerCar> pcars = getPcars();
        if (position < 0 || position > pcars.size()) {
            throw new IndexOutOfBoundsException("La posición ingresada es inválida");
        }
        pcars.add(position, pcar);
    }

    /**
     * metodo para eliminar un carro en una posicion especificada
     * @param train
     * @param position
     */
    public void removePcar(Train train, int position) {
        List <PassengerCar> pcars = train.getPcars();
        if (position < 0 || position > pcars.size()) {
            throw new IndexOutOfBoundsException("La posición ingresada es inválida");
        }
        pcars.remove(position);
    }

    /**
     * metodo para verificar si un tren cumple con los requisitos para conformar un tren
     * @return
     */
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

    /**
     * metodo para obtener la capacidad total de un tren
     * @return
     */
    public int fetchCapacity() {
        List<PassengerCar> pcars = getPcars();
        int capacity = 0;
        for (PassengerCar pcar : pcars) {
            capacity = pcar.getPassengerCapacity() + capacity;
        }
        return capacity;
    }

    /**
     * metodo para mostrar un tren por pantalla
     * @return
     */
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


