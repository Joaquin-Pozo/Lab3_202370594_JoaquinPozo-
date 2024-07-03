package org.example;

import org.example.metro.Subway;
import org.example.servicios.CargarLineas;

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
                    String fileName = "servicios/lineas.txt";
                    CargarLineas.loadLineFromFile(metro, fileName);
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
