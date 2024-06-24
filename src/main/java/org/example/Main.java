package org.example;
import java.util.ArrayList;
import java.util.List;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Creación de algunas instancias de Station
        Station station1 = new Station(0, "Baquedano", new StationType("t"), 30);
        Station station2 = new Station(1, "USACH", new StationType("r"), 30);
        Station station3 = new Station(2, "Estación Central", new StationType("c"), 45);
        Station station4 = new Station(3, "ULA", new StationType("r"), 60);
        Station station5 = new Station(4, "República", new StationType("c"), 62);
        Station station6 = new Station(5, "Los Héroes", new StationType("r"), 25);
        Station station7 = new Station(6, "Toesca", new StationType("c"), 95);
        Station station8 = new Station(7, "La Moneda", new StationType("r"), 64);
        Station station9 = new Station(8, "Cochera", new StationType("c"), 26);
        Station station10 = new Station(9, "Parque OHiggins", new StationType("r"), 45);
        Station station11 = new Station(10, "San Pablo", new StationType("c"), 95);
        Station station12 = new Station(11, "Los Dominicos", new StationType("t"), 26);

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

        // Req. 9
        if (line1.isLine(line1)) {
            System.out.println("\nline1 es una línea");
        } else {
            System.out.println("\nline1 no es una línea");
        }

        // Req. 10 creacion de PCar
        PassengerCar pcar1 = new PassengerCar(0, 90, "NS-74", "CAF", new CarType("tr"));
        PassengerCar pcar2 = new PassengerCar(1, 120, "NS-74", "CAF", new CarType("ct"));
        PassengerCar pcar3 = new PassengerCar(2, 100, "NS-74", "CAF", new CarType("ct"));
        PassengerCar pcar4 = new PassengerCar(3, 130, "NS-74", "CAF", new CarType("ct"));
        PassengerCar pcar5 = new PassengerCar(4, 110, "NS-74", "CAF", new CarType("ct"));
        PassengerCar pcar6 = new PassengerCar(5, 90, "NS-74", "CAF", new CarType("tr"));
        PassengerCar pcar7 = new PassengerCar(6, 90, "AS-62", "LCK", new CarType("tr"));
        PassengerCar pcar8 = new PassengerCar(7, 120, "AS-62", "LCK", new CarType("ct"));
        PassengerCar pcar9 = new PassengerCar(8, 100, "AS-62", "LCK", new CarType("ct"));
        PassengerCar pcar10 = new PassengerCar(9, 130, "AS-62", "LCK", new CarType("ct"));
        PassengerCar pcar11 = new PassengerCar(10, 110, "AS-62", "LCK", new CarType("ct"));
        PassengerCar pcar12 = new PassengerCar(11, 90, "AS-62", "LCK", new CarType("tr"));

        // Req. 11 creacion de train
        Train train1 = new Train(0, "CAF", 60, 100, new ArrayList<>());
        Train train2 = new Train(1, "LCK", 80, 70, new ArrayList<>());
        // Req. 12 addCar
        train1.addPcar(pcar1, 0);
        train1.addPcar(pcar2, 1);
        train1.addPcar(pcar3, 2);
        train1.addPcar(pcar4, 3);
        train1.addPcar(pcar5, 4);
        train1.addPcar(pcar6, 5);
        train2.addPcar(pcar7, 0);
        train2.addPcar(pcar8, 1);
        train2.addPcar(pcar9, 2);
        train2.addPcar(pcar10, 3);
        train2.addPcar(pcar11, 4);
        train2.addPcar(pcar12, 5);
        // Req. 13 removeCar
        train1.removePcar(train1, 0);
        System.out.println("Train 1: " + train1);
        // Req. 14 isTrain
        if (train1.isTrain()) {
            System.out.println("train1 es un tren");
        } else {
            System.out.println("train1 no es un tren");
        }
        train1.addPcar(pcar1, 0);
        // Req. 15 fetchCapacity
        System.out.println("La capacidad del train1 es: " + train1.fetchCapacity());
        // Req. 16 Driver constructor
        Driver driver1 = new Driver(0, "Tomás González", "CAF");
        Driver driver2 = new Driver(1, "Marcela Aravena", "CAF");
        Driver driver3 = new Driver(2, "Alicia González", "LCK");
        System.out.println("Nuevos Drivers: " + driver1 + ", " + driver2 + ", " + driver3);
        // Req. 17 Subway constructor
        Subway metro = new Subway(0, "Red de Metro Santiago");
        // Req. 18 Subway - addTrain
        metro.addTrain(train1, train2);
        System.out.println("Metro de Santiago: " + metro);



    }
}
