package org.example;

public class PassengerCar {
    private int id;
    private int passengerCapacity;
    private String model;
    private String trainMaker;
    private CarType carType;

    public PassengerCar(int id, int passengerCapacity, String model, String trainMaker, CarType carType) {
        this.id = id;
        this.passengerCapacity = passengerCapacity;
        this.model = model;
        this.trainMaker = trainMaker;
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", passengerCapacity=" + passengerCapacity +
                ", model='" + model + '\'' +
                ", trainMaker='" + trainMaker + '\'' +
                ", carType=" + carType +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public String getModel() {
        return model;
    }

    public String getTrainMaker() {
        return trainMaker;
    }

    public CarType getCarType() {
        return carType;
    }
}
