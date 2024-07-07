package org.example;

import org.example.subway.*;
import org.example.services.LoadFiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Subway metro = new Subway(1, "Red de metro de Santiago");
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("Menú:");
            System.out.println("1. Cargar información de la red de metro");
            System.out.println("2. Visualizar estado actual del sistema de metro");
            System.out.println("3. Interactuar con el sistema de metro");
            System.out.println("4. Salir del programa");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    LoadFiles newFile = new LoadFiles();
                    System.out.println("### Sistema Metro - Cargar información del sistema de metro ###");
                    System.out.println("1. Agregar líneas");
                    System.out.println("2. Agregar trenes con distinto número de carros");
                    System.out.println("3. Agregar conductores");
                    System.out.println("4. Asignar un conductor a un tren y una línea");
                    System.out.println("5. Retorno al menú de inicio");
                    int firstOption = scanner.nextInt();
                    scanner.nextLine();
                    switch (firstOption) {
                        case 1:
                            String linesFile = "src/main/java/org/example/services/lines.txt";
                            List<Line> lines = newFile.cargarLineas(linesFile);
                            for (Line line : lines) {
                                metro.addLine(line);
                            }
                            System.out.println("Lineas cargadas exitosamente");
                            break;
                        case 2:
                            String trainsFile = "src/main/java/org/example/services/trains.txt";
                            List<Train> trains = newFile.cargarTrenes(trainsFile);
                            for (Train train : trains) {
                                metro.addTrain(train);
                            }
                            System.out.println("Trenes cargados exitosamente");
                            break;
                        case 3:
                            String driversFile = "src/main/java/org/example/services/drivers.txt";
                            List<Driver> drivers = newFile.cargarConductores(driversFile);
                            for (Driver driver : drivers) {
                                metro.addDriver(driver);
                            }
                            System.out.println("Conductores cargados exitosamente");
                            break;
                        case 4:
                            System.out.println("Ingrese el id del tren");
                            int trainId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Ingrese el id de la linea");
                            int lineId = scanner.nextInt();
                            scanner.nextLine();
                            metro.assignTrainToLine(trainId, lineId);
                            System.out.println("Ingrese el id del conductor (debe tener la misma habilitación del tren)");
                            int driverId = scanner.nextInt();
                            scanner.nextLine();
                            Date time = new Date();
                            int departureStation = -1;
                            int arrivalStation = -1;
                            for (Line line : metro.getLines()) {
                                if (line.getId() == lineId) {
                                    List<Section> sections = line.getSections();
                                    Section firstSection = sections.get(0);
                                    Section lastSection = sections.get(sections.size() - 1);
                                    departureStation = firstSection.getPoint1().getId();
                                    arrivalStation = lastSection.getPoint2().getId();
                                    break;
                                }
                            }
                            metro.assignDriverToTrain(trainId, driverId, time, departureStation, arrivalStation);
                            System.out.println("Conductor asignado exitosamente");
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Opción no válida. Intente de nuevo");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("### Sistema Metro - Visualización del estado actual del sistema de metros ###");
                    System.out.println("1. Desplegar en pantalla el estado actual de la red de metros");
                    System.out.println("2. Retorno al menú de inicio");
                    int secondOption = scanner.nextInt();
                    scanner.nextLine();
                    if (secondOption == 1) {
                        System.out.println(metro);
                    }
                    break;
                case 3:
                    System.out.println("### Sistema Metro - Interactuar con el sistema de metros ###");
                    System.out.println("1. Obtener el largo total de una línea");
                    System.out.println("2. Determinar el trayecto entre una estación origen y final");
                    System.out.println("3. Determinar el costo total de recorrer una línea");
                    System.out.println("4. Determinar el costo de un trayecto entre estación origen y final");
                    System.out.println("5. Agregar una sección a una línea");
                    System.out.println("6. Verificar si una línea cumple con las restricciones especificadas para conformar una linea");
                    System.out.println("7. Añade un carro de pasajeros a un tren en la posición establecida");
                    System.out.println("8. Remueve un carro de pasajeros de un tren en la posición establecida");
                    System.out.println("9. Verifica si un tren cumple con las especificaciones de los carros de pasajeros");
                    System.out.println("10. Entrega la capacidad máxima de pasajeros de un tren");
                    System.out.println("11. Determina la ubicación de un tren a partir de una hora indicada del día");
                    System.out.println("12. Armar el recorrido del tren a partir de una hora especificada y que retorna la lista de estaciones futuras por recorrer");
                    System.out.println("13. Retorno al menú de inicio");
                    int thirdOption = scanner.nextInt();
                    scanner.nextLine();
                    switch (thirdOption) {
                        case 1:
                            System.out.println("Ingrese el id de la línea");
                            int lineId1 = scanner.nextInt();
                            boolean properId = false;
                            scanner.nextLine();
                            for (Line line : metro.getLines()) {
                                if (lineId1 == line.getId()) {
                                    int distanciaLinea = line.lineLength();
                                    System.out.println("La distancia total de la linea es: " + distanciaLinea);
                                    properId = true;
                                }
                            }
                            if (!properId) {
                                System.out.println("El id de la linea ingresada no existe en la red de metro");
                            }
                            break;
                        case 2:
                            System.out.println("Ingrese el id de la línea");
                            int lineId2 = scanner.nextInt();
                            boolean properId2 = false;
                            scanner.nextLine();
                            System.out.println("Ingrese el nombre de la estación de origen");
                            String station1 = scanner.nextLine();
                            System.out.println("Ingrese el nombre de la estación final");
                            String station2 = scanner.nextLine();
                            for (Line line : metro.getLines()) {
                                if (lineId2 == line.getId()) {
                                    int distancia = line.lineSectionLength(station1, station2);
                                    System.out.println("La distancia entre " + station1 + " y " + station2 + " es: " + distancia);
                                    properId2 = true;
                                    break;
                                }
                            }
                            if (!properId2) {
                                System.out.println("El id de la linea ingresada no existe en la red de metro");
                            }
                            break;
                        case 3:
                            System.out.println("Ingrese el id de la línea");
                            int lineId3 = scanner.nextInt();
                            boolean properId3 = false;
                            scanner.nextLine();
                            for (Line line : metro.getLines()) {
                                if (lineId3 == line.getId()) {
                                    int costoLinea = line.lineCost();
                                    System.out.println("El costo de recorrer la linea es: " + costoLinea);
                                    properId3 = true;
                                }
                            }
                            if (!properId3) {
                                System.out.println("El id de la linea ingresada no existe en la red de metro");
                            }
                            break;
                        case 4:
                            System.out.println("Ingrese el id de la línea");
                            int lineId4 = scanner.nextInt();
                            boolean properId4 = false;
                            scanner.nextLine();
                            System.out.println("Ingrese el nombre de la estación de origen");
                            String station11 = scanner.nextLine();
                            System.out.println("Ingrese el nombre de la estación final");
                            String station22 = scanner.nextLine();
                            for (Line line : metro.getLines()) {
                                if (lineId4 == line.getId()) {
                                    int costo = line.lineSectionCost(station11, station22);
                                    System.out.println("El costo entre " + station11 + " y " + station22 + " es: " + costo);
                                    properId4 = true;
                                    break;
                                }
                            }
                            if (!properId4) {
                                System.out.println("El id de la linea ingresada no existe en la red de metro");
                            }
                            break;
                        case 5:
                            // Ingreso de la primera estacion
                            System.out.println("Ingrese el id de la nueva estación (Point 1)");
                            int station1Id = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Ingrese el nombre de la nueva estación (Point 1)");
                            String station1Name = scanner.nextLine();
                            System.out.println("Ingrese el tipo de la nueva estación, r (regular), m (mantencion), c (combinacion), o t (terminal) (Point 1)");
                            String StringStation1Type = scanner.nextLine();
                            StationType station1Type = new StationType(StringStation1Type);
                            System.out.println("Ingrese el tiempo de parada de la nueva estación (Point 1)");
                            int station1StopTime = scanner.nextInt();
                            scanner.nextLine();
                            Station newStation1 = new Station(station1Id, station1Name, station1Type, station1StopTime);
                            // Ingreso de la segunda estacion
                            System.out.println("Ingrese el id de la nueva estación (Point 2)");
                            int station2Id = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Ingrese el nombre de la nueva estación (Point 2)");
                            String station2Name = scanner.nextLine();
                            System.out.println("Ingrese el tipo de la nueva estación (Point 2)");
                            String StringStation2Type = scanner.nextLine();
                            StationType station2Type = new StationType(StringStation2Type);
                            System.out.println("Ingrese el tiempo de parada de la nueva estación (Point 2)");
                            int station2StopTime = scanner.nextInt();
                            scanner.nextLine();
                            Station newStation2 = new Station(station2Id, station2Name, station2Type, station2StopTime);
                            // Ingreso de la nueva seccion
                            System.out.println("Ingrese la distancia de la nueva sección");
                            int sectionDistance = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Ingrese el costo de la nueva sección");
                            int sectionCost = scanner.nextInt();
                            scanner.nextLine();
                            // creacion de la nueva secciom
                            Section section = new Section(newStation1, newStation2, sectionDistance, sectionCost);
                            System.out.println("Ingrese el id de la línea donde desea agregar la nueva sección");
                            int lineId0 = scanner.nextInt();
                            scanner.nextLine();
                            boolean properLineId = false;
                            for (Line line : metro.getLines()) {
                                if (lineId0 == line.getId()) {
                                    line.lineAddSection(section);
                                    properLineId = true;
                                    System.out.println("Sección agregado exitosamente");
                                    break;
                                }
                            }
                            if (!properLineId) {
                                System.out.println("El id del tren ingresado no existe en la red de metro");
                            }
                            break;
                        case 6:
                            System.out.println("Ingrese el id de la línea");
                            int lineId5 = scanner.nextInt();
                            boolean properId5 = false;
                            scanner.nextLine();
                            for (Line line : metro.getLines()) {
                                if (lineId5 == line.getId()) {
                                    if (line.isLine(line)) {
                                        System.out.println("La linea ingresada cumple con las restricciones especificadas para conformar una linea");
                                    } else {
                                        System.out.println("La linea ingresada no cumple con las restricciones especificadas para conformar una linea");
                                    }
                                    properId5 = true;
                                    break;
                                }
                            }
                            if (!properId5) {
                                System.out.println("El id de la linea ingresada no existe en la red de metro");
                            }
                            break;
                        case 7:
                            System.out.println("Ingrese el id del nuevo carro");
                            int pcarId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Ingrese la capacidad de nuevo carro");
                            int pcarCapacity = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Ingrese el modelo del nuevo carro");
                            String pcarModel = scanner.nextLine();
                            System.out.println("Ingrese el fabricante del nuevo carro (train maker)");
                            String pcarTrainMaker = scanner.nextLine();
                            System.out.println("Ingrese el tipo del nuevo carro tr (terminal) o ct (central)");
                            String pcarType = scanner.nextLine();
                            CarType type = new CarType(pcarType);
                            PassengerCar newPcar = new PassengerCar(pcarId, pcarCapacity, pcarModel, pcarTrainMaker, type);
                            System.out.println("Ingrese la posición donde desea agregar el nuevo carro");
                            int position = scanner.nextInt();
                            System.out.println("Ingrese el id del tren");
                            int trainId = scanner.nextInt();
                            scanner.nextLine();
                            boolean properTrain = false;
                            for (Train train : metro.getTrains()) {
                                if (trainId == train.getId()) {
                                    train.addPcar(newPcar, position);
                                    properTrain = true;
                                    System.out.println("Carro agregado exitosamente");
                                    break;
                                }
                            }
                            if (!properTrain) {
                                System.out.println("El id del tren ingresado no existe en la red de metro");
                            }
                            break;
                        case 8:
                            System.out.println("Ingrese la posición donde desea eliminar el carro");
                            int position2 = scanner.nextInt();
                            System.out.println("Ingrese el id del tren");
                            int trainId2 = scanner.nextInt();
                            scanner.nextLine();
                            boolean properTrain2 = false;
                            for (Train train : metro.getTrains()) {
                                if (trainId2 == train.getId()) {
                                    train.removePcar(train, position2);
                                    properTrain2 = true;
                                    System.out.println("Carro eliminado exitosamente");
                                    break;
                                }
                            }
                            if (!properTrain2) {
                                System.out.println("El id del tren ingresado no existe en la red de metro");
                            }
                            break;
                        case 9:
                            System.out.println("Ingrese el id del tren");
                            int trainId3 = scanner.nextInt();
                            scanner.nextLine();
                            boolean properTrain3 = false;
                            for (Train train : metro.getTrains()) {
                                if (trainId3 == train.getId()) {
                                    if (train.isTrain()) {
                                        System.out.println("El tren ingresado cumple con las restricciones especificadas para conformar un tren");
                                    } else {
                                        System.out.println("El tren ingresado no cumple con las restricciones especificadas para conformar un tren");
                                    }
                                    properTrain3 = true;
                                    break;
                                }
                            }
                            if (!properTrain3) {
                                System.out.println("El id del tren ingresado no existe en la red de metro");
                            }
                            break;
                        case 10:
                            System.out.println("Ingrese el id del tren");
                            int trainId4 = scanner.nextInt();
                            scanner.nextLine();
                            boolean properTrain4 = false;
                            for (Train train : metro.getTrains()) {
                                if (trainId4 == train.getId()) {
                                    int trainCapacity = train.fetchCapacity();
                                    properTrain4 = true;
                                    System.out.println("La capacidad del tren es de: " + trainCapacity);
                                    break;
                                }
                            }
                            if (!properTrain4) {
                                System.out.println("El id del tren ingresado no existe en la red de metro");
                            }
                            break;
                        case 11:
                            System.out.println("Ingrese el id del tren");
                            int trainId5 = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Ingrese la hora de consulta en formato HH:mm:ss");
                            String timeString = scanner.nextLine();
                            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                            try {
                                // Parsear la cadena a un objeto Date
                                Date time = timeFormat.parse(timeString);
                                for (Train train : metro.getTrains()) {
                                    if (trainId5 == train.getId()) {
                                        System.out.println(metro.whereIsTrain(trainId5, time));
                                        break;
                                    }
                                }
                            } catch (ParseException e) {
                                System.out.println("Formato de hora incorrecto. Por favor ingrese la hora en formato HH:mm:ss");
                            }
                            break;
                        case 12:
                            System.out.println("Ingrese el id del tren");
                            int trainId6 = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Ingrese la hora de consulta en formato HH:mm:ss");
                            String timeString2 = scanner.nextLine();
                            SimpleDateFormat timeFormat2 = new SimpleDateFormat("HH:mm:ss");
                            try {
                                // Parsear la cadena a un objeto Date
                                Date time2 = timeFormat2.parse(timeString2);
                                for (Train train : metro.getTrains()) {
                                    if (trainId6 == train.getId()) {
                                        System.out.println("Las estaciones futuras por correr son: " + metro.trainPath(trainId6, time2));
                                        break;
                                    }
                                }
                            } catch (ParseException e) {
                                System.out.println("Formato de hora incorrecto. Por favor ingrese la hora en formato HH:mm:ss");
                            }
                            break;
                        case 13:
                            break;
                        default:
                            System.out.println("Opción no válida. Intente de nuevo");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Programa finalizado");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo");
                    break;
            }
        }
        scanner.close();
    }
}
