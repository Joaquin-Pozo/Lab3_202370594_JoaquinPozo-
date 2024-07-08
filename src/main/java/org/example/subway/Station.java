package org.example.subway;

public class Station {
    private int id;
    private String name;
    private StationType stationType;
    private int stopTime;

    /**
     * metodo constructor
     * @param id
     * @param name
     * @param stationType
     * @param stopTime
     */
    public Station(int id, String name, StationType stationType, int stopTime) {
        this.id = id;
        this.name = name;
        this.stationType = stationType;
        this.stopTime = stopTime;
    }

    /**
     * metodo para obtener id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * metodo para obtener nombre
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * metodo para obtener tiempo de parada
     * @return
     */
    public int getStopTime() {
        return stopTime;
    }

    /**
     * metodo para mostrar la estacion por pantalla
     * @return
     */
    @Override
    public String toString() {
        return String.format("Station{id=%d, name='%s', stationType='%s', stopTime=%d}",
                id, name, stationType.getType(), stopTime);
    }

}
