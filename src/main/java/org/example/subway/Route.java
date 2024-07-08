package org.example.subway;

import java.util.Date;

public class Route {
    int trainId;
    int lineId;
    int driverId;
    Date departureTime;
    int departureStation;
    int arrivalStation;

    /**
     * metodo constructor
      * @param trainId
     * @param lineId
     */
    public Route(int trainId, int lineId) {
        this.trainId = trainId;
        this.lineId = lineId;
        this.driverId = -1;
        this.departureTime = null;
        this.departureStation = -1;
        this.arrivalStation = -1;
    }

    /**
     * metodo para obtener el id del tren
     * @return
     */
    public int getTrainId() {
        return trainId;
    }

    /**
     * metodo para obtener el id de la linea
     * @return
     */
    public int getLineId() {
        return lineId;
    }

    /**
     * metodo para obtener el id del conductor
     * @return
     */
    public int getDriverId() {
        return driverId;
    }

    /**
     * metodo para obtener el tiempo de partida del tren
     * @return
     */
    public Date getDepartureTime() {
        return departureTime;
    }

    /**
     * metodo para obtener la estacion de partida
     * @return
     */
    public int getDepartureStation() {
        return departureStation;
    }

    /**
     * metodo para obtener la estacion de llegada
     * @return
     */
    public int getArrivalStation() {
        return arrivalStation;
    }

    /**
     * metodo para modificar el id del conductor
     * @param driverId
     */
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    /**
     * metodo para modificar la hora de partida
     * @param departureTime
     */
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * metodo para modificar la estacion de partida
     * @param departureStation
     */
    public void setDepartureStation(int departureStation) {
        this.departureStation = departureStation;
    }

    /**
     * metodo para modificar la estacion de llegada
     * @param arrivalStation
     */
    public void setArrivalStation(int arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    /**
     * metodo para mostrar la ruta por pantalla
     * @return
     */
    @Override
    public String toString() {
        return "\nRoute{" +
                "trainId=" + trainId +
                ", lineId=" + lineId +
                ", driverId=" + driverId +
                ", departureTime=" + departureTime +
                ", departureStation=" + departureStation +
                ", arrivalStation=" + arrivalStation +
                '}';
    }
}
