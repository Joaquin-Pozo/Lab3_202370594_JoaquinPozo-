package org.example.subway;

public class PassengerCar {
    private int id;
    private int passengerCapacity;
    private String model;
    private String trainMaker;
    private CarType carType;

    /**
     * metodo constructor
     * @param id
     * @param passengerCapacity
     * @param model
     * @param trainMaker
     * @param carType
     */
    public PassengerCar(int id, int passengerCapacity, String model, String trainMaker, CarType carType) {
        this.id = id;
        this.passengerCapacity = passengerCapacity;
        this.model = model;
        this.trainMaker = trainMaker;
        this.carType = carType;
    }

    /**
     * metodo para obtener la capacidad del carro
     * @return
     */
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    /**
     * metodo para obtener el modelo del carro
     * @return
     */
    public String getModel() {
        return model;
    }

    /**
     * metodo para obtener el fabricante del carro
     * @return
     */
    public String getTrainMaker() {
        return trainMaker;
    }

    /**
     * metodo para obtener el tipo de carro
     * @return
     */
    public CarType getCarType() {
        return carType;
    }

    /**
     * metodo para mostrar un carro por pantalla
     * @return
     */
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
}
