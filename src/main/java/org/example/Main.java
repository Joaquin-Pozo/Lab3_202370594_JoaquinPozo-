package org.example;

import org.example.script_pruebas.CargarEstaciones;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CargarEstaciones cargarEstaciones = new CargarEstaciones();

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Cargar las estaciones");
            System.out.println("2. Salir del programa");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    cargarEstaciones.start();
                    break;
                case 2:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        }
    }
}
