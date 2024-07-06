package org.example;

import org.example.metro.*;
import org.example.servicios.CargarArchivos;

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
                    CargarArchivos newFile = new CargarArchivos();
                    System.out.println("### Sistema Metro - Cargar información del sistema de metro ###");
                    System.out.println("1. Agregar líneas");
                    System.out.println("2. Agregar trenes con distinto número de carros");
                    System.out.println("3. Agregar conductores");
                    System.out.println("4. Asignar un conductor a un tren y una línea");
                    System.out.println("5. Retorno al menú de inicio");
                    int opcionCargar = scanner.nextInt();
                    scanner.nextLine();
                    if (opcionCargar == 1) {
                        String fileName = "src/main/java/org/example/servicios/lineas.txt";
                        List<Line> lines = newFile.cargarLineas(fileName);
                        for (Line line : lines) {
                            metro.addLine(line);
                        }
                        System.out.println("Lineas cargadas exitosamente");
                        break;
                    } else if (opcionCargar == 2) {
                        String fileName = "src/main/java/org/example/servicios/trenes.txt";
                        List<Train> trains = newFile.cargarTrenes(fileName);
                        for (Train train : trains) {
                            metro.addTrain(train);
                        }
                        System.out.println("Trenes cargados exitosamente");
                        break;
                    } else if (opcionCargar == 3) {
                        String fileName = "src/main/java/org/example/servicios/conductores.txt";
                        List<Driver> drivers = newFile.cargarConductores(fileName);
                        for (Driver driver : drivers) {
                            metro.addDriver(driver);
                        }
                        System.out.println("Conductores cargados exitosamente");
                        break;
                    } else if (opcionCargar == 4) {
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
                    } else if (opcionCargar == 5) {
                        break;
                    } else {
                        System.out.println("Opción no válida. Intente de nuevo");
                        break;
                    }
                case 2:
                    System.out.println("### Sistema Metro - Visualización del estado actual del sistema de metros ###");
                    System.out.println("1. Desplegar en pantalla el estado actual de la red de metros");
                    System.out.println("2. Retorno al menú de inicio");
                    int opcionVisualizar = scanner.nextInt();
                    scanner.nextLine();
                    if (opcionVisualizar == 1) {
                        System.out.println(metro);
                    }
                    break;
                case 3:
                    System.out.println("### Sistema Metro - Interactuar con el sistema de metros ###");
                    System.out.println("1. Obtener el largo total de una línea");
                    System.out.println("2. Determinar el trayecto entre una estación origen y final");
                    System.out.println("3. Determinar el costo total de recorrer una línea");
                    System.out.println("4. Determinar el costo de un trayecto entre estación origen y final");
                    System.out.println("5. Verificar si una línea cumple con las restricciones especificadas");
                    System.out.println("6. Añade un carro de pasajeros a un tren en la posición establecida");
                    System.out.println("7. Remueve un carro de pasajeros de un tren en la posición establecida");
                    System.out.println("8. Verifica si un tren cumple con las especificaciones de los carros de pasajeros.");
                    System.out.println("9. Entrega la capacidad máxima de pasajeros de un tren");
                    System.out.println("10. Determina la ubicación de un tren a partir de una hora indicada del día");
                    System.out.println("11. Armar el recorrido del tren a partir de una hora especificada y que retorna la lista de estaciones futuras por recorrer");
                    System.out.println("12. Retorno al menú de inicio");
                    int opcionInteraccion = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcionInteraccion) {
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
                        case 6:
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
                        case 7:
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
                        case 8:
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
                        case 9:
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
                        case 10:
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
                        case 11:
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
                        case 12:
                            break;
                        default:
                            System.out.println("Opción no válida. Intente de nuevo");
                    }
                    break;
                case 4:
                    System.out.println("Programa finalizado");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo");
            }
        }
        scanner.close();
    }
}
