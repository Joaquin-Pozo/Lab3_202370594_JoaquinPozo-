package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Creación de algunas instancias de Station
        Station station1 = new Station(0, "Baquedano", new Type("t"), 30);
        Station station2 = new Station(1, "USACH", new Type("r"), 30);
        Station station3 = new Station(2, "Estación Central", new Type("c"), 45);
        Station station4 = new Station(3, "ULA", new Type("r"), 60);
        Station station5 = new Station(4, "República", new Type("c"), 62);
        Station station6 = new Station(5, "Los Héroes", new Type("r"), 25);
        Station station7 = new Station(6, "Toesca", new Type("c"), 95);
        Station station8 = new Station(7, "La Moneda", new Type("r"), 64);
        Station station9 = new Station(8, "Cochera", new Type("c"), 26);
        Station station10 = new Station(9, "Parque OHiggins", new Type("r"), 45);
        Station station11 = new Station(10, "San Pablo", new Type("c"), 95);
        Station station12 = new Station(11, "Los Dominicos", new Type("t"), 26);

        // Creación de instancias de Section
        Section section1 = new Section(station1, station2, 10, 5);
        Section section2 = new Section(station2, station3, 52, 20);
        Section section3 = new Section(station3, station4, 32, 18);
        Section section4 = new Section(station4, station5, 22, 12);
        Section section5 = new Section(station5, station6, 18, 8);
        Section section6 = new Section(station6, station7, 30, 15);
        Section section7 = new Section(station7, station8, 12, 6);
        Section section8 = new Section(station8, station9, 15, 7);
        Section section9 = new Section(station9, station10, 28, 14);
        Section section10 = new Section(station10, station11, 35, 16);
        Section section11 = new Section(station11, station12, 42, 19);

        // Creación de una instancia de Line con secciones iniciales
        List<Section> initialSections = new ArrayList<>();
        initialSections.add(section1);
        initialSections.add(section2);
        initialSections.add(section3);
        initialSections.add(section4);
        initialSections.add(section5);
        initialSections.add(section6);
        initialSections.add(section7);
        initialSections.add(section8);
        initialSections.add(section9);
        initialSections.add(section10);
        initialSections.add(section11);
        Line line1 = new Line(1, "Linea 1", "UIC 60 ASCE", initialSections);
        Line line6 = new Line(6, "Linea 6", "200 R.E.", new ArrayList<>());
        // Req. 5
        String estacion1 = "Baquedano";
        String estacion2 = "Los Dominicos";
        int distancia = line1.lineSectionLength(estacion1, estacion2);
        // Req. 6
        var costoLinea1 = line1.lineCost();
        // Req. 7
        String estacion3 = "USACH";
        String estacion4 = "Toesca";
        var costoEstaciones = line1.lineSectionCost(estacion3, estacion4);
        // Req.8
        line6.lineAddSection(section1);
        line6.lineAddSection(section2);
        line6.lineAddSection(section3);
        line6.lineAddSection(section4);

        // Mostrar la línea
        System.out.println("Requerimiento 3:\n");
        System.out.println(line1);
        System.out.println(line6);
        // Largo de secciones
        System.out.println("\nRequerimiento 4:\n");
        System.out.println("El largo de la línea 1 es: " + line1.lineLength());
        System.out.println("El largo de la línea 6 es: " + line6.lineLength());
        System.out.println("La Distancia entre " + estacion1 + " y " + estacion2 + " es: " + distancia);
        System.out.println("El costo de recorrer la línea 1 es: " + costoLinea1);
        System.out.println("El costo entre " + estacion3 + " y " + estacion4 + " es: " + costoEstaciones);
    }
}
