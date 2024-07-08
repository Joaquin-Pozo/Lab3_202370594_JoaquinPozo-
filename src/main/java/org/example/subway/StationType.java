package org.example.subway;


public class StationType {
    String type;

    /**
     * metodo constructor
     * @param type
     */
    public StationType(String type) {
        if (isValidType(type)) {
            this.type = type;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * metodo para obtener el tipo de estacion
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * metodo para verificar si el String ingresado corresponde a un tipo de estacion
     * @param type
     * @return
     */
    protected boolean isValidType(String type) {
        return type.equals("r") || type.equals("m") || type.equals("c") || type.equals("t");
    }

}
