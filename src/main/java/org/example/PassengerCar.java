package org.example;

public class PassengerCar {
    int id;
    int passengerCapacity;
    String model;
    String trainMaker;
    CarType carType;

    public PassengerCar(int id, int passengerCapacity, String model, String trainMaker, CarType carType) {
        this.id = id;
        this.passengerCapacity = passengerCapacity;
        this.model = model;
        this.trainMaker = trainMaker;
        this.carType = carType;
    }
}
