package org.example.metro;

public class CarType extends StationType {

    public CarType(String type) {
        super(type);
    }

    @Override
    protected boolean isValidType(String type) {
        return type.equals("ct") || type.equals("tr");
    }

    @Override
    public String toString() {
        return type;
    }
}
