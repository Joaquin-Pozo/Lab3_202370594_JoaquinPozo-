package org.example;

public class CarType extends StationType {

    public CarType(String type) {
        super(type);
    }

    @Override
    protected boolean isValidType(String type) {
        return type.equals("ct") || type.equals("tr");
    }
}
