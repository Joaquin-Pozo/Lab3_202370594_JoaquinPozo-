package org.example;

import org.example.metro.Driver;
import org.example.metro.Line;
import org.example.metro.Subway;
import org.example.metro.Train;
import org.example.servicios.CargarArchivos;

import java.util.List;
import java.util.Scanner;

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
                    System.out.println("1. Agregar líneas");
                    System.out.println("2. Agregar trenes con distinto número de carros");
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
                    } else {
                        System.out.println("Opción no válida. Intente de nuevo");
                        break;
                    }
                case 2:
                    System.out.println(metro);
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Terminando el programa...");
                default:
                    System.out.println("Opción no válida. Intente de nuevo");
            }
        }
        scanner.close();
    }
}
