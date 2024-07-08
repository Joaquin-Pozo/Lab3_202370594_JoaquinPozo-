package org.example.subway;

public class CarType extends StationType {
    /**
     * metodo constructor
     * @param type
     */
    public CarType(String type) {
        super(type);
    }

    /**
     * metodo para verificar si el String ingresado corresponde a un tipo de carro
     * @param type
     * @return
     */
    @Override
    protected boolean isValidType(String type) {
        return type.equals("ct") || type.equals("tr");
    }

    /**
     * metodo para mostrar el carro por pantalla
     * @return
     */
    @Override
    public String toString() {
        return type;
    }
}
