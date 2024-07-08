package org.example.subway;

public class Section {

    private Station point1;
    private Station point2;
    private int distance;
    private int cost;

    /**
     * metodo constructor
     * @param point1
     * @param point2
     * @param distance
     * @param cost
     */
    public Section(Station point1, Station point2, int distance, int cost) {
        this.point1 = point1;
        this.point2 = point2;
        this.distance = distance;
        this.cost = cost;
    }

    /**
     * metodo para obtener estacion 1
     * @return
     */
    public Station getPoint1() {
        return point1;
    }

    /**
     * metodo para obtener estacion 2
     * @return
     */
    public Station getPoint2() {
        return point2;
    }

    /**
     * metodo para obtener distancia entre una estacion 1 y estacion 2
     * @return
     */
    public int getDistance() {
        return distance;
    }

    /**
     * metodo para obtener el costo entre una estacion 1 y estacion 2
     * @return
     */
    public int getCost() {
        return cost;
    }

    /**
     * metodo para mostrar la seccion por pantalla
     * @return
     */
    @Override
    public String toString() {
        return String.format("Section{point1=%s, point2=%s, distance=%d, cost=%d}",
                point1, point2, distance, cost);
    }
}
