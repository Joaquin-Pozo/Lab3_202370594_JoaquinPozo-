package org.example;

import java.util.Date;

public class Route {
    int trainId;
    int lineId;
    int driverId;
    Date departureTime;
    int departureStation;
    int arrivalStation;

    public Route(int trainId, int lineId) {
        this.trainId = trainId;
        this.lineId = lineId;
        this.driverId = -1;
        this.departureTime = null;
        this.departureStation = -1;
        this.arrivalStation = -1;
    }

    public int getTrainId() {
        return trainId;
    }

    public int getDriverId() {
        return driverId;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public int getDepartureStation() {
        return departureStation;
    }

    public int getArrivalStation() {
        return arrivalStation;
    }

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
