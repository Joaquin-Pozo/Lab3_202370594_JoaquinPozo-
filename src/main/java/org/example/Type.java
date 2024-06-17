package org.example;

public class Type {
    String type;
    public Type(String type) {
        if (isValidType(type)) {
            this.type = type;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getType() {
        return type;
    }

    private boolean isValidType(String type) {
        return type.equals("r") || type.equals("m") || type.equals("c") || type.equals("t");
    }

}
