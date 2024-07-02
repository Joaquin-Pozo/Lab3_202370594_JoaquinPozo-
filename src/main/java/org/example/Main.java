package org.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Creación de algunas instancias de Station
        Station station1 = new Station(0, "Baquedano", new StationType("t"), 330);
        Station station2 = new Station(1, "USACH", new StationType("r"), 360);
        Station station3 = new Station(2, "Estación Central", new StationType("c"), 495);
        Station station4 = new Station(3, "ULA", new StationType("r"), 650);
        Station station5 = new Station(4, "República", new StationType("c"), 692);
        Station station6 = new Station(5, "Los Héroes", new StationType("r"), 295);
        Station station7 = new Station(6, "Toesca", new StationType("c"), 955);
        Station station8 = new Station(7, "La Moneda", new StationType("r"), 694);
        Station station9 = new Station(8, "Cochera", new StationType("c"), 256);
        Station station10 = new Station(9, "Parque OHiggins", new StationType("r"), 452);
        Station station11 = new Station(10, "San Pablo", new StationType("c"), 955);
        Station station12 = new Station(11, "Los Dominicos", new StationType("t"), 264);
        // Creacion de instancias para nuevas estaciones -- Linea 6 simplificada
        Station station13 = new Station(12, "Cerrillos", new StationType("t"), 269);
        Station station14 = new Station(13, "Lo Valledor", new StationType("r"), 112);
        Station station15 = new Station(14, "Pdte. Pedro Aguirre Cerda", new StationType("c"), 552);
        Station station16 = new Station(15, "Franklin", new StationType("m"), 569);
        Station station17 = new Station(16, "Biobío", new StationType("c"), 482);
        Station station18 = new Station(17, "Ñuble", new StationType("r"), 592);
        Station station19 = new Station(18, "Estadio Nacional", new StationType("m"), 782);
        Station station20 = new Station(19, "Ñuñoa", new StationType("r"), 953);
        Station station21 = new Station(20, "Inés de Suárez", new StationType("c"), 781);
        Station station22 = new Station(21, "Los Leones", new StationType("t"), 226);

        // Creación de instancias de Section
        Section section1 = new Section(station1, station2, 100, 5);
        Section section2 = new Section(station2, station3, 520, 20);
        Section section3 = new Section(station3, station4, 326, 18);
        Section section4 = new Section(station4, station5, 223, 12);
        Section section5 = new Section(station5, station6, 182, 8);
        Section section6 = new Section(station6, station7, 309, 15);
        Section section7 = new Section(station7, station8, 121, 6);
        Section section8 = new Section(station8, station9, 159, 7);
        Section section9 = new Section(station9, station10, 282, 14);
        Section section10 = new Section(station10, station11, 356, 16);
        Section section11 = new Section(station11, station12, 426, 19);

        // Instancias Section para L6
        Section section12 = new Section(station13, station14, 299, 26);
        Section section13 = new Section(station14, station15, 672, 18);
        Section section14 = new Section(station15, station16, 948, 29);
        Section section15 = new Section(station16, station17, 266, 29);
        Section section16 = new Section(station17, station18, 512, 12);
        Section section17 = new Section(station18, station19, 299, 25);
        Section section18 = new Section(station19, station20, 729, 15);
        Section section19 = new Section(station20, station21, 593, 19);
        Section section20 = new Section(station21, station22, 894, 52);

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

        // Creacion de instancia Line para L6
        List<Section> line6Sections = new ArrayList<>();
        line6Sections.add(section12);
        line6Sections.add(section13);
        line6Sections.add(section14);
        line6Sections.add(section15);
        line6Sections.add(section16);
        line6Sections.add(section17);
        line6Sections.add(section18);
        line6Sections.add(section19);
        line6Sections.add(section20);

        Line line1 = new Line(1, "Linea 1", "UIC 60 ASCE", initialSections);
        Line line6 = new Line(6, "Linea 6", "200 R.E.", line6Sections);
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
        Train train1 = new Train(0, "CAF", 20, 709, new ArrayList<>());
        Train train2 = new Train(1, "LCK", 30, 950, new ArrayList<>());
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
        // Req. 19 Subway - addLine
        metro.addLine(line1);
        metro.addLine(line6);
        // Req. 20 subway - addDriver
        metro.addDriver(driver1, driver2, driver3);
        // Req. 22 assignTrainToLine
        metro.assignTrainToLine(0, 1);
        metro.assignTrainToLine(1, 6);
        // Req. 23 assignDriverToTrain
        Calendar calendar = new GregorianCalendar();

        // Establece la hora específica: por ejemplo, 14:30:00
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // Obtén la fecha y hora combinadas en una instancia de Date
        Date time = calendar.getTime();
        metro.assignDriverToTrain(0, 1, time, 0, 11);
        metro.assignDriverToTrain(1, 2, time, 12, 21);
        System.out.println("\n" + metro);

        // Req. 24 whereIsTrain
        Calendar calendarConsult = new GregorianCalendar();

        // Establece la hora específica: por ejemplo, 17:00:00
        calendarConsult.set(Calendar.HOUR_OF_DAY, 17);
        calendarConsult.set(Calendar.MINUTE, 0);
        calendarConsult.set(Calendar.SECOND, 0);
        calendarConsult.set(Calendar.MILLISECOND, 0);
        // Obtén la fecha y hora combinadas en una instancia de Date
        Date timeConsult = calendarConsult.getTime();

        String whereIsTrain1 = metro.whereIsTrain(0, timeConsult);
        System.out.println("\n" + whereIsTrain1);

        String whereIsTrain2 = metro.whereIsTrain(1, timeConsult);
        System.out.println("\n" + whereIsTrain2);

        // Req. 25 trainPath
        List<String> trainPath1 = metro.trainPath(0, timeConsult);
        System.out.println("\nEstaciones que le quedan por recorrer al tren 0 a las " + timeConsult + ": " + trainPath1);

        List<String> trainPath2 = metro.trainPath(1, timeConsult);
        System.out.println("\nEstaciones que le quedan por recorrer al tren 1 a las " + timeConsult + ": " + trainPath2);

    }
}
