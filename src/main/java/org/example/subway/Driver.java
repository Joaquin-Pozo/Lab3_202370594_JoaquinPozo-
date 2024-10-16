package org.example.subway;

public class Driver {
    private int id;
    private String name;
    private String trainMaker;

    /**
     * metodo constructor
     * @param id
     * @param name
     * @param trainMaker
     */
    public Driver(int id, String name, String trainMaker) {
        this.id = id;
        this.name = name;
        this.trainMaker = trainMaker;
    }

    /**
     * metodo para obtener id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * metodo para obtener la habilitacion del conductor
     * @return
     */
    public String getTrainMaker() {
        return trainMaker;
    }

    /**
     * metodo para mostrar el conductor por pantalla
     * @return
     */
    @Override
    public String toString() {
        return String.format("Driver{id=%d, name='%s', trainMaker='%s'}", id, name, trainMaker);
    }
}
