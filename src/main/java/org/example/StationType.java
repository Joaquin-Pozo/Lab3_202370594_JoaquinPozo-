package org.example;


public class StationType {
    String type;

    public StationType(String type) {
        if (isValidType(type)) {
            this.type = type;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getType() {
        return type;
    }

    protected boolean isValidType(String type) {
        return type.equals("r") || type.equals("m") || type.equals("c") || type.equals("t");
    }

}
