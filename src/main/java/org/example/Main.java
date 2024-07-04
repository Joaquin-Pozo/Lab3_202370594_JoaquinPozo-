package org.example;

import org.example.metro.Line;
import org.example.metro.Subway;
import org.example.servicios.CargarArchivos;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Subway metro = new Subway(1, "Red de Metro de Santiago");
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (option != 3) {
            System.out.println("Menú:");
            System.out.println("1. Cargar línea desde archivo");
            System.out.println("2. Mostrar líneas");
            System.out.println("3. Terminar");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    String fileName = "src/main/java/org/example/servicios/lineas.txt";
                    CargarArchivos newFile = new CargarArchivos();
                    List<Line> lines = newFile.cargarLineas(fileName);
                    for (Line line : lines) {
                        metro.addLine(line);
                    }
                    System.out.println("Lineas cargadas exitosamente en " + metro.getName());
                    break;
                case 2:
                    System.out.println(metro);
                    break;
                case 3:
                    System.out.println("Terminando el programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }
}
