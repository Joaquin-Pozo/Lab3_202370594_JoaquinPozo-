package org.example;

import org.example.metro.*;
import org.example.servicios.CargarArchivos;

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
                            int idLinea = scanner.nextInt();
                            scanner.nextLine();
                            for (Line line : metro.getLines()) {
                                if (idLinea == line.getId()) {
                                    int distanciaLinea = line.lineLength();
                                    System.out.println("La distancia total de la linea es: " + distanciaLinea);
                                    break;
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Ingrese el id de la línea");
                            int lineId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Ingrese el nombre de la estación de origen");
                            String station1 = scanner.next();
                            scanner.nextLine();
                            System.out.println("Ingrese el nombre de la estación final");
                            String station2 = scanner.next();
                            scanner.nextLine();
                            for (Line line : metro.getLines()) {
                                if (lineId == line.getId()) {
                                    int distancia = line.lineSectionLength(station1, station2);
                                    System.out.println("La distancia entre " + station1 + " y " + station2 + " es: " + distancia);
                                  break;
                                }
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
