package org.example.subway;

public class Section {

    private Station point1;
    private Station point2;
    private int distance;
    private int cost;

    public Section(Station point1, Station point2, int distance, int cost) {
        this.point1 = point1;
        this.point2 = point2;
        this.distance = distance;
        this.cost = cost;
    }

    public Station getPoint1() {
        return point1;
    }

    public Station getPoint2() {
        return point2;
    }

    public int getDistance() {
        return distance;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("Section{point1=%s, point2=%s, distance=%d, cost=%d}",
                point1, point2, distance, cost);
    }

}
