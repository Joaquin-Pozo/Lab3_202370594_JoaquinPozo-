package org.example;
//import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Type type = new Type("t");

        Station estacion1 = new Station(0, "Estación Central", type, 20);

        var id = estacion1.getId();
        var name = estacion1.getName();
        var stationType = estacion1.getStationType().getType();
        var stopTime = estacion1.getStopTime();

        System.out.println("Estación de metro:\nNombre: " + name + "\nId: " + id + "\nTiempo de parada: " + stopTime + "\nTipo de estación: " + stationType);
        }
    }